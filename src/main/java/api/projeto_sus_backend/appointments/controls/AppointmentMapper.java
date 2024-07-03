package api.projeto_sus_backend.appointments.controls;

import api.projeto_sus_backend.appointments.entities.Appointment;
import api.projeto_sus_backend.appointments.entities.AppointmentSchema;
import api.projeto_sus_backend.doctor.controls.DoctorMapper;

/**
 * The Class AppointmentMapper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
public class AppointmentMapper {

    public static AppointmentSchema convert(Appointment appointment) {
        return new AppointmentSchema.Builder().builder()
                .setId(appointment.getId())
                .setDate(appointment.getDate())
                .setHour(appointment.getHour())
                .setDoctor(DoctorMapper.convert(appointment.getDoctor()))
                .build();
    }

    public static Appointment convert(AppointmentSchema appointmentSchema) {
        return new Appointment.Builder().builder()
                .setId(appointmentSchema.getId())
                .setDate(appointmentSchema.getDate())
                .setHour(appointmentSchema.getHour())
                .setDoctor(DoctorMapper.convert(appointmentSchema.getDoctor()))
                .build();
    }

    public static Appointment convert(AppointmentProjections.Page appointmentPage) {
        return new Appointment.Builder().builder()
                .setId(appointmentPage.getId())
                .setDate(appointmentPage.getDate())
                .setHour(appointmentPage.getHour())
                .setDoctor(DoctorMapper.convert(appointmentPage.getDoctor()))
                .build();
    }
}
