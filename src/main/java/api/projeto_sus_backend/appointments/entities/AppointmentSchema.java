package api.projeto_sus_backend.appointments.entities;

import api.projeto_sus_backend.doctor.entities.DoctorSchema;
import api.projeto_sus_backend.generic.entities.GenericSchema;
import api.projeto_sus_backend.pacient.entities.PacientSchema;
import jakarta.persistence.*;

import java.time.LocalDate;
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
    private String hour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", foreignKey = @ForeignKey(name = "fk_appointment_x_doctor"))
    private DoctorSchema doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacient_id", foreignKey = @ForeignKey(name = "fk_appointment_x_pacient"))
    private PacientSchema pacient;

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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public DoctorSchema getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorSchema doctor) {
        this.doctor = doctor;
    }

    public PacientSchema getPacient() {
        return pacient;
    }

    public void setPacient(PacientSchema pacient) {
        this.pacient = pacient;
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

        public Builder setHour(String hour) {
            this.appointmentSchema.setHour(hour);
            return this;
        }

        public Builder setDoctor(DoctorSchema doctor) {
            this.appointmentSchema.setDoctor(doctor);
            return this;
        }

        public Builder setPacient(PacientSchema pacient) {
            this.appointmentSchema.setPacient(pacient);
            return this;
        }

        public AppointmentSchema build() {
            return this.appointmentSchema;
        }
    }
}
