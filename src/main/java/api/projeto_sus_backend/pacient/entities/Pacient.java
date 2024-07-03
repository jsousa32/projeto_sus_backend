package api.projeto_sus_backend.pacient.entities;

import api.projeto_sus_backend.appointments.entities.Appointment;
import api.projeto_sus_backend.pacient.controls.PacientProjections;
import api.projeto_sus_backend.user.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The Class Pacient
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pacient extends User {

    @NotBlank(message = "Número do SUS obrigatório")
    @Pattern(regexp = "\\d{15}$", message = "Número do SUS inválido")
    @JsonView({PacientProjections.Page.class, PacientProjections.Resume.class, PacientProjections.ResumeToAppointment.class})
    private String susNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Appointment> appointments;

    public String getSusNumber() {
        return susNumber;
    }

    public void setSusNumber(String susNumber) {
        this.susNumber = susNumber;
    }

    public List<Appointment> getAppointments() {
        if (Objects.isNull(this.appointments)) {
            this.appointments = new ArrayList<>();
        }

        return this.appointments;
    }

    /**
     * The Builder of Pacient
     */
    public static class Builder {

        private Pacient pacient;


        public Builder builder() {
            this.pacient = new Pacient();

            return this;
        }

        public Builder setId(UUID id) {
            this.pacient.setId(id);
            return this;
        }

        public Builder setSusNumber(String susNumber) {
            this.pacient.setSusNumber(susNumber);
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.pacient.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            this.pacient.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            this.pacient.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            this.pacient.setPassword(password);
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.pacient.setTelephone(telephone);
            return this;
        }

        public Builder setDocument(String document) {
            this.pacient.setDocument(document);
            return this;
        }

        public Builder setAppointments(List<Appointment> appointments) {
            this.pacient.getAppointments().addAll(appointments);
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.pacient.setCreatedAt(createdAt);
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.pacient.setUpdatedAt(updatedAt);
            return this;
        }

        public Builder setDisabledAt(LocalDateTime disabledAt) {
            this.pacient.setDisabledAt(disabledAt);
            return this;
        }

        public Builder setDisabled(boolean disabled) {
            this.pacient.setDisabled(disabled);
            return this;
        }

        public Pacient build() {
            return this.pacient;
        }
    }
}
