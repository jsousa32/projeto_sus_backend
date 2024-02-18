package api.sus.controlller;

import api.sus.business.AuthBusiness;
import api.sus.model.record.AccessToken;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class AuthController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@RestController
@RequestMapping(path = "auth")
public class AuthController {

    private final AuthBusiness authBusiness;

    @Autowired
    public AuthController(AuthBusiness authBusiness) {
        this.authBusiness = authBusiness;
    }

    @PostMapping("login")
    public ResponseEntity<AccessToken> login() {
        AccessToken response = authBusiness.login();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("confirm-email-pacient")
    public ResponseEntity<AccessToken> confirmPacientEmail(
            @RequestParam("token") final String token
    ) {
        authBusiness.confirmPacientEmail(token);

        AccessToken response = authBusiness.login();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("forget-password")
    public ResponseEntity<Void> forgetPassword(
            @RequestParam("email") @NotBlank(message = "O e-mail é obrigatório na requisição") @Email(message = "E-mail no formato inválido")
            final String email
    ) {
        authBusiness.forgetPassword(email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("reset-password")
    public ResponseEntity<Void> resetPassword() {
        authBusiness.resetPassword();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

