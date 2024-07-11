package api.projeto_sus_backend.appointments.boundary;

import api.projeto_sus_backend.application.controls.ApplicationException;
import api.projeto_sus_backend.appointments.controls.AppointmentExceptions;
import api.projeto_sus_backend.appointments.controls.AppointmentGateway;
import api.projeto_sus_backend.appointments.entities.Appointment;
import api.projeto_sus_backend.doctor.controls.DoctorGateway;
import api.projeto_sus_backend.pacient.controls.PacientGateway;
import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.utils.helpers.JwtHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The Class AppointmentBusiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Component
public class AppointmentBusiness {

    private final AppointmentGateway appointmentGateway;

    private final DoctorGateway doctorGateway;

    private final PacientGateway pacientGateway;

    public AppointmentBusiness(AppointmentGateway appointmentGateway,
                               DoctorGateway doctorGateway,
                               PacientGateway pacientGateway) {
        this.appointmentGateway = appointmentGateway;
        this.doctorGateway = doctorGateway;
        this.pacientGateway = pacientGateway;
    }

    /**
     * Metodo responsável por criar uma consulta
     *
     * @param authenticationToken
     * @param pacientId           ;
     * @param doctorId            ;
     * @param appointment         ;
     */
    @Transactional(rollbackFor = ApplicationException.class)
    public void save(JwtAuthenticationToken authenticationToken, UUID pacientId, UUID doctorId, Appointment appointment) {

        validationHourAndDateAppointment(appointment);

        Pacient pacient = pacientGateway.findById(pacientId);

        validationPacientHasAnyAppointmentTodayOrWeek(pacient, appointment.getDate());

        List<Appointment> appointments = appointmentGateway.findAllAppointmentsByDoctorId(doctorId);

        int quantityAppointmentsPerHour = validationQuantityAppointmentPerHour(appointment, appointments);

        if(quantityAppointmentsPerHour == 2 && !JwtHelper.isAdministrator(authenticationToken)) {
            throw new AppointmentExceptions.HasntPermission();
        }

        appointment.setDoctor(doctorGateway.findById(doctorId));

        appointment = appointmentGateway.save(appointment);

        pacient.getAppointments().add(appointment);

        pacientGateway.save(pacient);
    }


    /**
     * Metodo responsável por retornar todas as consultas paginadas
     *
     * @param filter;
     * @param pageable;
     * @return Page<Appointment>;
     */
    public Page<Appointment> findAll(String filter, Pageable pageable) {
        return appointmentGateway.findAll(filter, pageable);
    }

    /**
     * Metodo responsável por buscar uma consulta
     *
     * @param id;
     * @return Appointment;
     */
    public Appointment findById(UUID id) {
        return appointmentGateway.findByIdResume(id);
    }


    /**
     * Metodo responsável por atualizar uma consulta
     *
     * @param authenticationToken
     * @param id                  ;
     * @param appointment         ;
     */
    public void update(JwtAuthenticationToken authenticationToken, UUID id, Appointment appointment) {

        validationHourAndDateAppointment(appointment);

        Pacient pacient = pacientGateway.findByAppointmentId(id);

        validationPacientHasAnyAppointmentTodayOrWeek(pacient, appointment.getDate());

        Appointment appointmentDB = appointmentGateway.findById(id);

        List<Appointment> appointments = appointmentGateway.findAllAppointmentsByDoctorId(appointmentDB.getDoctor().getId());

        validationQuantityAppointmentPerHour(appointment, appointments);

        int quantityAppointmentsPerHour = validationQuantityAppointmentPerHour(appointment, appointments);

        if(quantityAppointmentsPerHour == 2 && !JwtHelper.isAdministrator(authenticationToken)) {
            throw new AppointmentExceptions.HasntPermission();
        }

        BeanUtils.copyProperties(appointment, appointmentDB, "id", "doctor");

        appointmentGateway.save(appointmentDB);
    }

    /**
     * Metodo responsável por deletar uma consulta
     *
     * @param id;
     */
    public void delete(UUID id) {
        appointmentGateway.delete(id);
    }

    /**
     * Metodo responsável por validar se o paciente já possui uma consulta no dia e/ou na semana
     *
     * @param pacient;
     * @param date;
     */
    private void validationPacientHasAnyAppointmentTodayOrWeek(Pacient pacient, LocalDate date) {

        boolean hasAppointmentToday = pacient.getAppointments().stream().anyMatch(a -> a.getDate().equals(date));

        if (hasAppointmentToday) {
            throw new AppointmentExceptions.HasAppointmentAtDate();
        }

        LocalDate startDayOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        LocalDate endDayOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

        boolean hasAppointmentOnWeek = pacient.getAppointments().stream()
                .anyMatch(a -> a.getDate().isAfter(startDayOfWeek) && a.getDate().isBefore(endDayOfWeek));

        if (hasAppointmentOnWeek) {
            throw new AppointmentExceptions.HasAppointmentAtWeek();
        }
    }

    /**
     * Metodo responsável por validar a quantidade de consultas de um médico numa determinada data e hora
     *
     * @param appointment;
     * @param appointments;
     * @return
     */
    private static int validationQuantityAppointmentPerHour(Appointment appointment, List<Appointment> appointments) {
        List<Appointment> appointmentsByDateAndHour =
                appointments.stream().filter(a ->
                                Objects.equals(a.getHour(), appointment.getHour()) &&
                                a.getDate().equals(appointment.getDate()))
                        .toList();

        if (appointmentsByDateAndHour.size() == 3) {
            throw new AppointmentExceptions.FullTime();
        }

        return appointmentsByDateAndHour.size();
    }

    /**
     * Metodo responsável por validar a data e horário da consulta
     *
     * @param appointment;
     */
    private static void validationHourAndDateAppointment(Appointment appointment) {
        if (appointment.getDate().isBefore(LocalDate.now())) {
            throw new AppointmentExceptions.InvalidHourAndDate();
        }

        LocalTime parsedHour = LocalTime.parse(appointment.getHour(), DateTimeFormatter.ofPattern("HH:mm"));

        if (parsedHour.isBefore(LocalTime.now())) {
            throw new AppointmentExceptions.InvalidHourAndDate();
        }
    }
}
