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
            @RequestParam("codeEmailConfirmation") String codeEmailConfirmation
    ) {
        authBusiness.emailConfirmation(authenticationToken, codeEmailConfirmation);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
