package api.sus.model.dto;

import api.sus.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private UserRole role;

    @JsonIgnore
    private boolean confirmedEmail = false;

    @JsonIgnore
    private String tokenEmail = RandomStringUtils.random(6, false, true);

    public void encryptPassword() {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
