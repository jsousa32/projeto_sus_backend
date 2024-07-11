package api.projeto_sus_backend.application.controls.mail;

import api.projeto_sus_backend.application.entities.ForgotPassword;
import api.projeto_sus_backend.application.entities.ModelCustomConfiguration;
import api.projeto_sus_backend.user.entities.User;
import api.projeto_sus_backend.utils.CryptographyUtils;
import api.projeto_sus_backend.utils.TemplatesPath;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class MailService
 *
 * @author João Lucas Silva de Sousa
 * @sincer 09/07/2024
 */
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    private final Configuration configuration;

    private final ModelCustomConfiguration modelCustomConfiguration;

    public MailService(JavaMailSender javaMailSender,
                       Configuration configuration,
                       ModelCustomConfiguration modelCustomConfiguration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
        this.modelCustomConfiguration = modelCustomConfiguration;
    }

    public MailBuilder builder() {
        return new MailBuilder(configuration, javaMailSender);
    }

    public void emailConfirmation(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", user.getCodeEmailConfirmation());
        params.put("name", user.getFirstName() + " " + user.getLastName());

        this.builder().to(user.getEmail()).subject("Token de Confirmação").send(TemplatesPath.AUTH.EMAIL_CONFIRMATION, params);
    }

    public void registerPassword(User user) {
        String frontendUrlApplication = modelCustomConfiguration.getFrontendUrlApplication();

        String url = frontendUrlApplication
                .concat("register-password?userId=" + CryptographyUtils.encrypt(user.getId(), modelCustomConfiguration.getSecret()));

        Map<String, Object> params = new HashMap<>();
        params.put("url", url);
        params.put("name", user.getFirstName() + " " + user.getLastName());

        this.builder().to(user.getEmail()).subject("Cadastrar Senha").send(TemplatesPath.AUTH.REGISTER_PASSWORD, params);
    }

    public void forgotPassword(User user, ForgotPassword forgotPassword) {
        String frontendUrlApplication = modelCustomConfiguration.getFrontendUrlApplication();

        String url = frontendUrlApplication
                .concat("reset?forgotId=" + CryptographyUtils.encrypt(forgotPassword.getId(), modelCustomConfiguration.getSecret()))
                .concat("&userId" + CryptographyUtils.encrypt(forgotPassword.getUserId(), modelCustomConfiguration.getSecret()))
                .concat("&expiresAt" + forgotPassword.getExpiresAt());

        Map<String, Object> params = new HashMap<>();
        params.put("url", url);
        params.put("name", user.getFirstName() + " " + user.getLastName());

        this.builder().to(user.getEmail()).subject("Recuperação de Senha").send(TemplatesPath.AUTH.FORGOT_PASSWORD, params);
    }
}
