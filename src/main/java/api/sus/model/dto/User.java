package api.sus.model.dto;

import api.sus.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * The Class User
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Generic {

    @NotBlank(message = "O primeiro nome é obrigatório")
    private String firstName;

    @NotBlank(message = "O sobrenome nome é obrigatório")
    private String lastName;

    @NotBlank(message = "O e-email é obrigatório")
    @Email(message = "O e-mail está no fomrato inválido")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O telefone deve possuir apenas 11 caracteres númericos")
    private String telephone;

    @Pattern(regexp = "\\d{6}|\\d{15}", message = "O documento deve possuir apenas 6 ou 15 caracteres númericos, referente ao CRM e ao Número SUS")
    private String document;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private UserRole role;

    public void encryptPassword() {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
