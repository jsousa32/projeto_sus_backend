package api.projeto_sus_backend.doctor.controls;

import api.projeto_sus_backend.doctor.entities.Doctor;
import api.projeto_sus_backend.doctor.entities.DoctorSchema;

/**
 * The Class DoctorMapper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class DoctorMapper {

    public static DoctorSchema convert(Doctor doctor) {
        return new DoctorSchema.Builder().builder()
                .setId(doctor.getId())
                .setFirstName(doctor.getFirstName())
                .setLastName(doctor.getLastName())
                .setEmail(doctor.getEmail())
                .setTelephone(doctor.getTelephone())
                .setDocument(doctor.getDocument())
                .setCrm(doctor.getCrm())
                .setPassword(doctor.getPassword())
                .setDisabledAt(doctor.getDisabledAt())
                .setDisabled(doctor.isDisabled())
                .build();
    }

    public static Doctor convert(DoctorSchema doctorSchema) {
        return new Doctor.Builder().builder()
                .setId(doctorSchema.getId())
                .setFirstName(doctorSchema.getFirstName())
                .setLastName(doctorSchema.getLastName())
                .setEmail(doctorSchema.getEmail())
                .setTelephone(doctorSchema.getTelephone())
                .setDocument(doctorSchema.getDocument())
                .setCrm(doctorSchema.getCrm())
                .setPassword(doctorSchema.getPassword())
                .setDisabledAt(doctorSchema.getDisabledAt())
                .setDisabled(doctorSchema.isDisabled())
                .build();
    }

    public static Doctor convert(DoctorProjections.Page doctorPage) {
        return new Doctor.Builder().builder()
                .setId(doctorPage.getId())
                .setFirstName(doctorPage.getFirstName())
                .setLastName(doctorPage.getLastName())
                .setEmail(doctorPage.getEmail())
                .setTelephone(doctorPage.getTelephone())
                .setCrm(doctorPage.getCrm())
                .build();
    }

    public static Doctor convert(DoctorProjections.Resume doctorResume) {
        return new Doctor.Builder().builder()
                .setId(doctorResume.getId())
                .setFirstName(doctorResume.getFirstName())
                .setLastName(doctorResume.getLastName())
                .setEmail(doctorResume.getEmail())
                .setTelephone(doctorResume.getTelephone())
                .setDocument(doctorResume.getDocument())
                .setCrm(doctorResume.getCrm())
                .build();
    }

    public static Doctor convert(DoctorProjections.ResumeToAppointments doctorResumeAppointment) {
        return new Doctor.Builder().builder()
                .setId(doctorResumeAppointment.getId())
                .setFirstName(doctorResumeAppointment.getFirstName())
                .setLastName(doctorResumeAppointment.getLastName())
                .setCrm(doctorResumeAppointment.getCrm())
                .build();
    }
}
