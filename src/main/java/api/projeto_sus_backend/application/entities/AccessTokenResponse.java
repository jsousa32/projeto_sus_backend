package api.projeto_sus_backend.application.entities;

import java.time.LocalDateTime;

/**
 * The Class AccessTokenResponse
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class AccessTokenResponse {

    private String accessToken;

    private boolean emailConfirmed;

    private String name;

    private LocalDateTime expiresAt;

    public AccessTokenResponse(String accessToken, boolean emailConfirmed, String name, LocalDateTime expiresAt) {
        this.accessToken = accessToken;
        this.emailConfirmed = emailConfirmed;
        this.name = name;
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
