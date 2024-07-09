package api.projeto_sus_backend.application.controls.mail;

import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public MailService(JavaMailSender javaMailSender, Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    public MailBuilder builder() {
        return new MailBuilder(configuration, javaMailSender);
    }
}
