package api.projeto_sus_backend.application.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class SwaggerConfiguration
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(this.info());
    }

    private Info info() {
        return new Info()
                .title("Projeto Sus Backend API")
                .description("Projeto para o gerenciamento de consultas para uma UBS (Unidade Básica de Saúde)")
                .contact(this.contact())
                .version("1.0");
    }

    private Contact contact() {
        return new Contact()
                .name("João Lucas")
                .email("jsousa.quimica@gmail.com");
    }
}
