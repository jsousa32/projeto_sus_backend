package api.projeto_sus_backend.doctor.entities;

import api.projeto_sus_backend.pacient.entities.PacientSchema;
import api.projeto_sus_backend.user.entities.UserSchema;
import api.projeto_sus_backend.user.entities.enums.Permissions;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The Class DoctorSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@Entity
@Table(name = "tb_doctors", indexes = {
        @Index(columnList = "crm")
})
@DiscriminatorValue(UserSchema.DESCIMINATOR_DOCTOR)
public class DoctorSchema extends UserSchema {

    @Column(length = 6, unique = true, nullable = false)
    private String crm;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    /**
     * The Builder of DoctorSchema
     */
    public static class Builder {

        private DoctorSchema doctorSchema;

        public Builder builder() {
            this.doctorSchema = new DoctorSchema();
            return this;
        }

        public Builder setId(UUID id) {
            this.doctorSchema.setId(id);
            return this;
        }

        public Builder setCrm(String crm) {
            this.doctorSchema.setCrm(crm);
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.doctorSchema.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            this.doctorSchema.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            this.doctorSchema.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            this.doctorSchema.setPassword(password);
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.doctorSchema.setTelephone(telephone);
            return this;
        }

        public Builder setDocument(String document) {
            this.doctorSchema.setDocument(document);
            return this;
        }

        public Builder setPermissions(List<Permissions> permissions) {
            this.doctorSchema.getPermissions().addAll(permissions);
            return this;
        }

        public Builder setEmailConfirmed(boolean emailConfirmed) {
            this.doctorSchema.setEmailConfirmed(emailConfirmed);
            return this;
        }

        public Builder setCodeEmailConfirmation(String codeEmailConfirmation) {
            this.doctorSchema.setCodeEmailConfirmation(codeEmailConfirmation);
            return this;
        }

        public Builder setDisabledAt(LocalDateTime disabledAt) {
            this.doctorSchema.setDisabledAt(disabledAt);
            return this;
        }

        public Builder setDisabled(boolean disabled) {
            this.doctorSchema.setDisabled(disabled);
            return this;
        }

        public DoctorSchema build() {
            return this.doctorSchema;
        }
    }
}
