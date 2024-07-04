package api.projeto_sus_backend.appointments.controls;

import api.projeto_sus_backend.appointments.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * The Class AppointmentGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Component
public class AppointmentGateway {

    private final AppointmentRepository appointmentRepository;

    public AppointmentGateway(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void save(Appointment appointment) {
        appointmentRepository.save(AppointmentMapper.convert(appointment));
    }

    public Appointment saveAndReturn(Appointment appointment) {
        return AppointmentMapper.convert(appointmentRepository.save(AppointmentMapper.convert(appointment)));

    }

    public List<Appointment> findAllAppointmentsByDoctorId(UUID doctorId) {
        return appointmentRepository.findAllAppointmentsByDoctorId(doctorId).stream().map(AppointmentMapper::convert).toList();
    }

    public Page<Appointment> findAll(String filter, Pageable pageable) {
        return appointmentRepository.findAll(filter, pageable).map(AppointmentMapper::convert);
    }

    public Appointment findByIdResume(UUID id) {
        return appointmentRepository.findByIdResume(id).map(AppointmentMapper::convert)
                .orElseThrow(AppointmentExceptions.NotFound::new);
    }

    public Appointment findById(UUID id) {
        return appointmentRepository.findById(id).map(AppointmentMapper::convert)
                .orElseThrow(AppointmentExceptions.NotFound::new);
    }

    public void delete(UUID id) {
        appointmentRepository.deleteById(id);
    }
}