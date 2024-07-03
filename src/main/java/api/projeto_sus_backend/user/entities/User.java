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
import org.hibernate.validator.constraints.br.CPF;

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

    @NotBlank(message = "Nome é obrigatório")
    @JsonView(UserProjections.FirstName.class)
    private String firstName;

    @NotBlank(message = "Sobrenome é obrigatório")
    @JsonView(UserProjections.LastName.class)
    private String lastName;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @JsonView(UserProjections.Email.class)
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonView(UserProjections.Password.class)
    private String password;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{11}$", message = "Telefone inválido")
    @JsonView(UserProjections.Telephone.class)
    private String telephone;

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    @JsonView(UserProjections.Document.class)
    private String document;


    @JsonIgnore
    @JsonView(UserProjections.Document.class)
    private List<Permissions> permissions = new ArrayList<>();

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
}
