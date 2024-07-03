package api.projeto_sus_backend.admin.entities;

import api.projeto_sus_backend.user.entities.UserSchema;
import api.projeto_sus_backend.user.entities.enums.Permissions;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

/**
 * The Class AdminSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Entity
@Table(name = "tb_admins")
@DiscriminatorValue(UserSchema.DESCRIMINATOR_ADMIN)
public class AdminSchema extends UserSchema {

    /**
     * The Builder of AdminSchema
     */
    public static class Builder {
        private AdminSchema adminSchema;

        public Builder builder() {
            this.adminSchema = new AdminSchema();
            return this;
        }

        public Builder setId(UUID id) {
            this.adminSchema.setId(id);
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.adminSchema.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            this.adminSchema.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            this.adminSchema.setEmail(email);
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.adminSchema.setTelephone(telephone);
            return this;
        }

        public Builder setPassword(String password) {
            this.adminSchema.setPassword(password);
            return this;
        }

        public Builder setDocument(String document) {
            this.adminSchema.setDocument(document);
            return this;
        }

        public Builder setPermissions(List<Permissions> permissions) {
            this.adminSchema.getPermissions().addAll(permissions);
            return this;
        }

        public AdminSchema build() {
            return this.adminSchema;
        }
    }
}
