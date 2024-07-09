package api.projeto_sus_backend.application.controls.mail;

import api.projeto_sus_backend.application.controls.ApplicationException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The Class MailBuilder
 *
 * @author João Lucas Silva de Sousa
 * @sincer 09/07/2024
 */
public class MailBuilder {

    private final static String NOREPLY = "noreply@fakesus.com.br";

    private final Configuration configuration;

    private final JavaMailSender javaMailSender;

    private String subject;

    private String[] to;

    public MailBuilder(Configuration configuration,
                       JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public MailBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailBuilder to(String to) {
        this.to = to.split("");
        return this;
    }

    public MailBuilder to(List<String> to) {
        this.to = to.toArray(new String[0]);
        return this;
    }

    public void send(String path, Map<String, Object> params) {
        try {
            validations();

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            String template = template(path, params);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setFrom(NOREPLY);

            mimeMessageHelper.setTo(to);

            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.setText(template, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new MailExceptions.ErrorToSend();
        }
    }

    private String template(String path, Map<String, Object> params) {
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(path), params);
        } catch (IOException | TemplateException e) {
            throw new MailExceptions.ErrorToConvertTemplate();
        }
    }

    private void validations() {
        if (Objects.isNull(to)) {
            throw new MailExceptions.InvalidDestination();
        }

        if (Objects.isNull(subject)) {
            throw new MailExceptions.InvalidSubject();
        }
    }
}
