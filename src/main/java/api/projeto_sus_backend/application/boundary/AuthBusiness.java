package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.controls.ApplicationException;
import api.projeto_sus_backend.application.controls.mail.MailService;
import api.projeto_sus_backend.application.entities.UserSession;
import api.projeto_sus_backend.application.entities.ModelCustomConfiguration;
import api.projeto_sus_backend.user.controls.UserGateway;
import api.projeto_sus_backend.user.entities.User;
import api.projeto_sus_backend.utils.CryptographyUtils;
import api.projeto_sus_backend.utils.helpers.JwtHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    private final MailService mailService;

    private final ModelCustomConfiguration modelCustomConfiguration;

    public AuthBusiness(JwtService jwtService,
                        UserGateway userGateway,
                        MailService mailService,
                        ModelCustomConfiguration modelCustomConfiguration) {
        this.jwtService = jwtService;
        this.userGateway = userGateway;
        this.mailService = mailService;
        this.modelCustomConfiguration = modelCustomConfiguration;
    }

    /**
     * Metodo responsável por realizar o login na aplicação
     *
     * @return AccessTokenResponse;
     */
    public UserSession login(Authentication authentication) {
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
    @Transactional(rollbackFor = ApplicationException.class)
    public void forgotPassword(String email) {
        User user = userGateway.findByEmail(email);

        mailService.forgotPassword(user);
    }

    /**
     * Metodo responsável por realizar o resete da senha do usuário
     *
     * @param encryptedUserId;
     * @param password
     */
    public void resetPassword(String encryptedUserId, String password) {
        String decryptUserId = CryptographyUtils.decrypt(encryptedUserId, modelCustomConfiguration.getSecret());

        UUID userId = UUID.fromString(decryptUserId);

        User user = userGateway.findById(userId);

        password = CryptographyUtils.decrypt(password, modelCustomConfiguration.getSecret());

        user.setPassword(password);

        user.encryptPassword();

        userGateway.save(user);
    }
}
