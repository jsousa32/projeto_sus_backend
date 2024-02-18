package api.sus.service.mail;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import api.sus.exception.GlobalException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * The Class JavaMailBuilder
 *
 * @author João Lucas Silva de Sousa
 * @sincer 14/02/2024
 */
@Component
public class JavaMailBuilder {

    private final static String NOREPLY = "noreply@fakesus.com.br";

    private final Configuration configuration;

    private final JavaMailSender javaMailSender;

    private String subject;

    private String to;

    @Autowired
    public JavaMailBuilder(Configuration configuration,
                           JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    private String template(final String name, final Map<String, Object> params) {
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(name), params);
        } catch (TemplateException | IOException e) {
            throw new GlobalException("Erro ao gerar o template", HttpStatus.BAD_REQUEST);
        }
    }

    public JavaMailBuilder subject(String parameter) {
        subject = parameter;

        return this;
    }

    public JavaMailBuilder to(String parameter) {
        to = parameter;

        return this;
    }

    public void fire(String path, Map<String, Object> params) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            String template = template(path, params);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setFrom(NOREPLY);

            mimeMessageHelper.setTo(to);

            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.setText(template, true);

            javaMailSender.send(mimeMessage);
        } catch (GlobalException | MessagingException e) {
            throw new GlobalException("Erro ao enviar o e-mail " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
