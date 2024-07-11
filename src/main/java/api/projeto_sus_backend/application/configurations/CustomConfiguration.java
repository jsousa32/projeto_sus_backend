package api.projeto_sus_backend.application.configurations;

import api.projeto_sus_backend.application.entities.ModelCustomConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class CustomConfiguration
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
@Configuration
public class CustomConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "custom")
    public ModelCustomConfiguration configuration() {
        return new ModelCustomConfiguration();
    }
}
