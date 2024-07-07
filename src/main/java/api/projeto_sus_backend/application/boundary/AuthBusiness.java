package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.entities.AuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * The Class AuthBusiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
@Component
public class AuthBusiness {

    private final JwtService jwtService;

    public AuthBusiness(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Metodo responsável por realizar o login na aplicação
     *
     * @return AccessTokenResponse;
     */
    public AuthResponse login(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
