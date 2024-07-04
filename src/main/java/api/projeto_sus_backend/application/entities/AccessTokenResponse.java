package api.projeto_sus_backend.application.entities;

/**
 * The Class AccessTokenResponse
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class AccessTokenResponse {

    private String accessToken;

    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
