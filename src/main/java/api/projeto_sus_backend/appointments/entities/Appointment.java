package api.projeto_sus_backend.appointments.entities;

import api.projeto_sus_backend.doctor.entities.Doctor;
import api.projeto_sus_backend.doctor.entities.DoctorSchema;
import api.projeto_sus_backend.generic.entities.Generic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * The Class Appointment
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class Appointment extends Generic {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Data da consulta é obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotBlank(message = "Horário da consulta é obrigatório")
    @JsonFormat(pattern = "hh:MM")
    private LocalTime hour;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Doctor doctor;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

        public Builder setHour(LocalTime hour) {
            this.appointment.setHour(hour);
            return this;
        }

        public Builder setDoctor(Doctor doctor) {
            this.appointment.setDoctor(doctor);
            return this;
        }

        public Appointment build() {
            return this.appointment;
        }
    }
}
