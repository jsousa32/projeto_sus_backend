package api.projeto_sus_backend.admin.entities;

import api.projeto_sus_backend.user.entities.User;
import api.projeto_sus_backend.user.entities.enums.Permissions;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.UUID;

/**
 * The Class Admin
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Admin extends User {

    /**
     * The Builder of Admin
     */
    public static class Builder {
        private Admin admin;

        public Builder builder() {
            this.admin = new Admin();
            return this;
        }

        public Builder setId(UUID id) {
            this.admin.setId(id);
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.admin.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            this.admin.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            this.admin.setEmail(email);
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.admin.setTelephone(telephone);
            return this;
        }

        public Builder setPassword(String password) {
            this.admin.setPassword(password);
            return this;
        }

        public Builder setDocument(String document) {
            this.admin.setDocument(document);
            return this;
        }

        public Builder setPermissions(List<Permissions> permissions) {
            this.admin.getPermissions().addAll(permissions);
            return this;
        }

        public Admin build() {
            return this.admin;
        }
    }
}
