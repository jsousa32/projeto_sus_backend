package api.projeto_sus_backend.user.entities;

import api.projeto_sus_backend.generic.entities.Generic;
import api.projeto_sus_backend.user.controls.UserProjections;
import api.projeto_sus_backend.user.entities.enums.Permissions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Class User
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class User extends Generic {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(UserProjections.Id.class)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório", groups = UserProjections.FirstName.class)
    @JsonView(UserProjections.FirstName.class)
    private String firstName;

    @NotBlank(message = "Sobrenome é obrigatório", groups = UserProjections.LastName.class)
    @JsonView(UserProjections.LastName.class)
    private String lastName;

    @NotBlank(message = "Email é obrigatório", groups = UserProjections.Email.class)
    @Email(message = "Email inválido")
    @JsonView(UserProjections.Email.class)
    private String email;

    @NotBlank(message = "Senha é obrigatório", groups = UserProjections.Password.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonView(UserProjections.Password.class)
    private String password;

    @NotBlank(message = "Telefone é obrigatório", groups = UserProjections.Telephone.class)
    @Pattern(regexp = "\\d{11}$", message = "Telefone inválido")
    @JsonView(UserProjections.Telephone.class)
    private String telephone;

    @NotBlank(message = "CPF é obrigatório", groups = UserProjections.Document.class)
    @CPF(message = "CPF inválido")
    @JsonView(UserProjections.Document.class)
    private String document;

    @JsonIgnore
    @JsonView(UserProjections.Permission.class)
    private final List<Permissions> permissions = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(UserProjections.EmailConfirmed.class)
    private boolean emailConfirmed = false;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(UserProjections.CodeEmailConfirmation.class)
    private final String codeEmailConfirmation = RandomStringUtils.random(6, false, true);

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void encryptPassword() {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getCodeEmailConfirmation() {
        return codeEmailConfirmation;
    }

    /**
     * The Builder of User
     */
    public static class Builder {
        private User user;

        public Builder builder() {
            this.user = new User();
            return this;
        }

        public Builder setId(UUID id) {
            this.user.setId(id);
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.user.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            this.user.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            this.user.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            this.user.setPassword(password);
            return this;
        }

        public Builder setTelephone(String telephone) {
            this.user.setTelephone(telephone);
            return this;
        }

        public Builder setDocument(String document) {
            this.user.setDocument(document);
            return this;
        }

        public Builder setPermissions(List<Permissions> permissions) {
            this.user.getPermissions().addAll(permissions);
            return this;
        }

        public Builder setEmailConfirmed(boolean emailConfirmed) {
            this.user.setEmailConfirmed(emailConfirmed);
            return this;
        }

        public Builder setCodeEmailConfirmation(String codeEmailConfirmation) {
            this.setCodeEmailConfirmation(codeEmailConfirmation);
            return this;
        }

        public User build() {
            return this.user;
        }
    }
}
