package api.projeto_sus_backend.appointments.entities;

import api.projeto_sus_backend.appointments.controls.AppointmentProjections;
import api.projeto_sus_backend.doctor.controls.DoctorProjections;
import api.projeto_sus_backend.doctor.entities.Doctor;
import api.projeto_sus_backend.generic.entities.Generic;
import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.utils.annotations.ValidTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The Class Appointment
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class Appointment extends Generic {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(AppointmentProjections.Page.class)
    private UUID id;

    @NotNull(message = "Data da consulta é obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonView({AppointmentProjections.Page.class, AppointmentProjections.Create.class})
    private LocalDate date;

    @ValidTime
    @Schema(defaultValue = "08:00")
    @JsonView({AppointmentProjections.Page.class, AppointmentProjections.Create.class})
    private String hour;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(DoctorProjections.ResumeToAppointments.class)
    private Doctor doctor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(DoctorProjections.ResumeToAppointments.class)
    private Pacient pacient;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    /**
     * The Builder of Appointment
     */
    public static class Builder {
        private Appointment appointment;

        public Builder builder() {
            this.appointment = new Appointment();
            return this;
        }

        public Builder setId(UUID id) {
            this.appointment.setId(id);
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.appointment.setDate(date);
            return this;
        }

        public Builder setHour(String hour) {
            this.appointment.setHour(hour);
            return this;
        }

        public Builder setDoctor(Doctor doctor) {
            this.appointment.setDoctor(doctor);
            return this;
        }

        public Builder setPacient(Pacient pacient) {
            this.appointment.setPacient(pacient);
            return this;
        }

        public Appointment build() {
            return this.appointment;
        }
    }
}
