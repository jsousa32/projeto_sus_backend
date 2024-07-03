package api.projeto_sus_backend.doctor.entities;

import api.projeto_sus_backend.doctor.controls.DoctorProjections;
import api.projeto_sus_backend.user.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class Doctor
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor extends User {

    @NotBlank(message = "Número do CRM obrigatório")
    @Pattern(regexp = "\\d{6}$", message = "Número do CRM inválido")
    @JsonView({DoctorProjections.Page.class, DoctorProjections.Resume.class, DoctorProjections.ResumeToAppointments.class})
    private String crm;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    /**
     * The Builder of Doctor
     */
    public static class Builder {

        private Doctor doctor;

        public Builder builder() {
            this.doctor = new Doctor();
            return this;
        }

        public Builder setId(UUID id) {
            this.doctor.setId(id);
            return this;
        }

        public Builder setCrm(String crm) {
            this.doctor.setCrm(crm);
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.doctor.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            this.doctor.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            this.doctor.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            this.doctor.setPassword(password);
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.doctor.setTelephone(telephone);
            return this;
        }

        public Builder setDocument(String document) {
            this.doctor.setDocument(document);
            return this;
        }

        public Builder setDisabledAt(LocalDateTime disabledAt) {
            this.doctor.setDisabledAt(disabledAt);
            return this;
        }

        public Builder setDisabled(boolean disabled) {
            this.doctor.setDisabled(disabled);
            return this;
        }

        public Doctor build() {
            return this.doctor;
        }
    }
}
