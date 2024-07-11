package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.controls.mail.MailService;
import api.projeto_sus_backend.application.controls.password.ForgotPasswordExceptions;
import api.projeto_sus_backend.application.controls.password.ForgotPasswordGateway;
import api.projeto_sus_backend.application.entities.AuthResponse;
import api.projeto_sus_backend.application.entities.ForgotPassword;
import api.projeto_sus_backend.application.entities.ModelCustomConfiguration;
import api.projeto_sus_backend.user.controls.UserGateway;
import api.projeto_sus_backend.user.entities.User;
import api.projeto_sus_backend.utils.CryptographyUtils;
import api.projeto_sus_backend.utils.helpers.JwtHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

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

    private final ForgotPasswordGateway forgotPasswordGateway;

    private final MailService mailService;

    private final ModelCustomConfiguration modelCustomConfiguration;

    public AuthBusiness(JwtService jwtService,
                        UserGateway userGateway,
                        ForgotPasswordGateway forgotPasswordGateway,
                        MailService mailService,
                        ModelCustomConfiguration modelCustomConfiguration) {
        this.jwtService = jwtService;
        this.userGateway = userGateway;
        this.forgotPasswordGateway = forgotPasswordGateway;
        this.mailService = mailService;
        this.modelCustomConfiguration = modelCustomConfiguration;
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

    /**
     * Metodo responsável por realizar o envio de email para recuperação da senha
     *
     * @param email;
     */
    public void forgotPassword(String email) {
        User user = userGateway.findByEmail(email);

        ForgotPassword forgotPassword = new ForgotPassword(user.getId());

        forgotPassword = forgotPasswordGateway.save(forgotPassword);

        mailService.forgotPassword(user, forgotPassword);
    }

    /**
     * Metodo responsável por realizar o resete da senha do usuário
     *
     * @param forgotId;
     * @param userId;
     * @param password
     */
    public void resetPassword(UUID forgotId, UUID userId, String password) {
        ForgotPassword forgotPassword = forgotPasswordGateway.findById(forgotId);

        if (!Objects.equals(forgotPassword.getUserId(), userId)) {
            throw new ForgotPasswordExceptions.UserIdInvalid();
        }

        if(forgotPassword.getExpiresAt().isAfter(LocalDateTime.now())) {
            throw new ForgotPasswordExceptions.UrlExpired();
        }

        User user = userGateway.findById(userId);

        password = CryptographyUtils.decrypt(password, modelCustomConfiguration.getSecret(), String.class);

        user.setPassword(password);

        user.encryptPassword();

        userGateway.save(user);
    }
}
