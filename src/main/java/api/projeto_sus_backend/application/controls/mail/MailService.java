package api.projeto_sus_backend.application.controls.mail;

import api.projeto_sus_backend.application.entities.ForgotPassword;
import api.projeto_sus_backend.application.entities.ModelCustomConfiguration;
import api.projeto_sus_backend.user.entities.User;
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

    public void forgotPassword(User user, ForgotPassword forgotPassword) {
        String frontendUrlApplication = modelCustomConfiguration.getFrontendUrlApplication();

        String url = frontendUrlApplication
                .concat("reset?token=" + forgotPassword.getId())
                .concat("&userId" + forgotPassword.getUserId())
                .concat("&expiresAt" + forgotPassword.getExpiresAt());

        Map<String, Object> params = new HashMap<>();
        params.put("url", url);
        params.put("name", user.getFirstName() + " " + user.getLastName());

        this.builder().to(user.getEmail()).subject("Recuperação de Senha").send(TemplatesPath.AUTH.FORGOT_PASSWORD, params);
    }
}
