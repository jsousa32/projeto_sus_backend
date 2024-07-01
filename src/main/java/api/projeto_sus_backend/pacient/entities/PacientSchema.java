package api.projeto_sus_backend.pacient.entities;

import api.projeto_sus_backend.user.entities.UserSchema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class PacientSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Entity
@Table(name = "tb_pacients", indexes = {
        @Index(columnList = "susNumber")
})
@DiscriminatorValue(UserSchema.DESCIMINATOR_PACIENT)
public class PacientSchema extends UserSchema {

    @Column(name = "sus_number", length = 15, unique = true, nullable = false)
    private String susNumber;

    public String getSusNumber() {
        return susNumber;
    }

    private void setSusNumber(String susNumber) {
        this.susNumber = susNumber;
    }

    /**
     * The Builder of PacientSchema
     */
    public static class Builder {

        private PacientSchema pacientSchema;


        public Builder builder() {
            this.pacientSchema = new PacientSchema();
            return this;
        }

        public Builder setId(UUID id) {
            this.pacientSchema.setId(id);
            return this;
        }

        public Builder setSusNumber(String susNumber) {
            this.pacientSchema.setSusNumber(susNumber);
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.pacientSchema.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            this.pacientSchema.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            this.pacientSchema.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            this.pacientSchema.setPassword(password);
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.pacientSchema.setTelephone(telephone);
            return this;
        }

        public Builder setDocument(String document) {
            this.pacientSchema.setDocument(document);
            return this;
        }

        public Builder setDisabledAt(LocalDateTime disabledAt) {
            this.pacientSchema.setDisabledAt(disabledAt);
            return this;
        }

        public Builder setDisabled(boolean disabled) {
            this.pacientSchema.setDisabled(disabled);
            return this;
        }

        public PacientSchema build() {
            return this.pacientSchema;
        }
    }
}
