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

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }


    /**
     * The Builder of AccessTokenResponse
     */
    public static class Builder {
        private AccessTokenResponse accessTokenResponse;

        public Builder builder() {
            this.accessTokenResponse = new AccessTokenResponse();
            return this;
        }

        public Builder accessToken(String accessToken) {
            this.accessTokenResponse.accessToken = accessToken;
            return this;
        }

        public Builder emailConfirmed(boolean emailConfirmed) {
            this.accessTokenResponse.emailConfirmed = emailConfirmed;
            return this;
        }

        public Builder name(String firstName, String lastName) {
            this.accessTokenResponse.name = firstName + " " + lastName;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.accessTokenResponse.createdAt = createdAt;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.accessTokenResponse.expiresAt = expiresAt;
            return this;
        }

        public AccessTokenResponse build() {
            return this.accessTokenResponse;
        }
    }
}
