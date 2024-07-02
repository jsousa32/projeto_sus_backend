package api.projeto_sus_backend.appointments.entities;

import api.projeto_sus_backend.doctor.entities.Doctor;
import api.projeto_sus_backend.generic.entities.Generic;
import api.projeto_sus_backend.utils.annotations.ValidTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

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
    private UUID id;

    @NotBlank(message = "Data da consulta é obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ValidTime
    @Schema(defaultValue = "08:00")
    private String hour;

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

        public Appointment build() {
            return this.appointment;
        }
    }
}
