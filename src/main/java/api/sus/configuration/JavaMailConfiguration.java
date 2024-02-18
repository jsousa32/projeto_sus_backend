package api.sus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * The Class JavaMailConfiguration
 *
 * @author João Lucas Silva de Sousa
 * @sincer 14/02/2024
 */
@Configuration
public class JavaMailConfiguration {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("noreplyfakesus@gmail.com");
        javaMailSender.setPassword("bgdenmdthteuyczt");
        javaMailSender.setJavaMailProperties(getProperties());

        return javaMailSender;
    }

    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

    private Properties getProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return properties;
    }
}
