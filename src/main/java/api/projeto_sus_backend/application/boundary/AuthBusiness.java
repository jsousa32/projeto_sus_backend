package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.entities.AuthResponse;
import api.projeto_sus_backend.user.controls.UserExceptions;
import api.projeto_sus_backend.user.controls.UserGateway;
import api.projeto_sus_backend.user.entities.User;
import api.projeto_sus_backend.utils.helpers.JwtHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * The Class AuthBusiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
@Component
public class AuthBusiness {

    private final JwtService jwtService;
    private final UserGateway userGateway;

    public AuthBusiness(JwtService jwtService, UserGateway userGateway) {
        this.jwtService = jwtService;
        this.userGateway = userGateway;
    }

    /**
     * Metodo responsável por realizar o login na aplicação
     *
     * @return AccessTokenResponse;
     */
    public AuthResponse login(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

    /**
     * Metodo responsável por realizar a confirmação do email do usuário
     *
     * @param authenticationToken;
     * @param codeEmailConfirmation;
     */
    public void emailConfirmation(JwtAuthenticationToken authenticationToken, String codeEmailConfirmation) {
        String email = JwtHelper.getUserEmail(authenticationToken);

        User user = userGateway.findByEmail(email);

        if (user.isEmailConfirmed()) {
            throw new AuthExceptions.EmailAlreadyConfirmed();
        }

        if (!Objects.equals(user.getCodeEmailConfirmation(), codeEmailConfirmation)) {
            throw new AuthExceptions.CodeConfirmationEmailNotValid();
        }

        user.setEmailConfirmed(true);

        userGateway.save(user);
    }
}
