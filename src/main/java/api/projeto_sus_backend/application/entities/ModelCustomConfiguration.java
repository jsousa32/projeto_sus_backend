package api.projeto_sus_backend.application.entities;

/**
 * The Class ModelCustomConfiguration
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
public class ModelCustomConfiguration {

    private String frontendUrlApplication;

    private String secret;

    public String getFrontendUrlApplication() {
        return frontendUrlApplication;
    }

    public void setFrontendUrlApplication(String frontendUrlApplication) {
        this.frontendUrlApplication = frontendUrlApplication;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
