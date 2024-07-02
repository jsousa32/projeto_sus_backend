package api.projeto_sus_backend.appointments.entities;

import api.projeto_sus_backend.doctor.entities.DoctorSchema;
import api.projeto_sus_backend.generic.entities.GenericSchema;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * The Class AppointmentSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@Entity
@Table(name = "tb_appointments", indexes = {
        @Index(columnList = "pacient_id"),
        @Index(columnList = "doctor_id"),
})
public class AppointmentSchema extends GenericSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 30, nullable = false)
    private LocalDate date;

    @Column(length = 20, nullable = false)
    private LocalTime hour;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "doctor_id", foreignKey = @ForeignKey(name = "fk_appointment_x_doctor"))
    private DoctorSchema doctor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public DoctorSchema getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorSchema doctor) {
        this.doctor = doctor;
    }

    /**
     * The Builder of AppointmentSchema
     */
    public static class Builder {
        private AppointmentSchema appointmentSchema;

        public Builder builder() {
            this.appointmentSchema = new AppointmentSchema();
            return this;
        }

        public Builder setId(UUID id) {
            this.appointmentSchema.setId(id);
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.appointmentSchema.setDate(date);
            return this;
        }

        public Builder setHour(LocalTime hour) {
            this.appointmentSchema.setHour(hour);
            return this;
        }

        public Builder setDoctor(DoctorSchema doctor) {
            this.appointmentSchema.setDoctor(doctor);
            return this;
        }

        public AppointmentSchema build() {
            return this.appointmentSchema;
        }
    }
}