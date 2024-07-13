package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.entities.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * The Class AuthController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
@RestController
@RequestMapping("v1/auth")
@Tag(name = "Modulo de Autenticação")
public class AuthController {

    private final AuthBusiness authBusiness;

    public AuthController(AuthBusiness authBusiness) {
        this.authBusiness = authBusiness;
    }

    /**
     * Endpoint responsável por realizar o login do usuário
     *
     * @return ResponseEntity<AccessTokenResponse>;
     */
    @Operation(summary = "Endpoint responsável por realizar o login do usuário")
    @PostMapping
    public ResponseEntity<AuthResponse> login(Authentication authentication) {
        AuthResponse response = authBusiness.login(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint responsável por realizar a confirmação de email do usuário
     *
     * @param authenticationToken;
     * @param codeEmailConfirmation;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por realizar a confirmação de email do usuário")
    @PostMapping("email-confirmation")
    public ResponseEntity<Void> emailConfirmation(
            JwtAuthenticationToken authenticationToken,
            @RequestParam(name = "codeEmailConfirmation") String codeEmailConfirmation
    ) {
        authBusiness.emailConfirmation(authenticationToken, codeEmailConfirmation);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável por realizar a recuperação de senha do usuário
     *
     * @param email;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por realizar a recuperação de senha do usuário")
    @PostMapping("forgot")
    public ResponseEntity<Void> forgotPassword(
            @RequestParam(name = "email") String email
    ) {
        authBusiness.forgotPassword(email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Endpoint responsável por realizar o resete da senha do usuário")
    @PostMapping("reset")
    public ResponseEntity<Void> resetPassword(
            @RequestParam(name = "userId") String encryptedUserId,
            @RequestParam(name = "password") String password
    ) {
        authBusiness.resetPassword(encryptedUserId, password);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
