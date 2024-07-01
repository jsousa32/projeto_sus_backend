package api.projeto_sus_backend.pacient.entities;

import api.projeto_sus_backend.user.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

/**
 * The Class Pacient
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class Pacient extends User {

    @NotBlank(message = "Número do SUS obrigatório")
    @Pattern(regexp = "\\d{15}$", message = "Número do SUS inválido")
    private String susNumber;

    public String getSusNumber() {
        return susNumber;
    }

    public void setSusNumber(String susNumber) {
        this.susNumber = susNumber;
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
