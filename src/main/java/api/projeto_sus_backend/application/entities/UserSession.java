package api.projeto_sus_backend.application.entities;

import api.projeto_sus_backend.user.entities.enums.Permissions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class AccessTokenResponse
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class UserSession {

    private String accessToken;

    private boolean emailConfirmed;

    private String name;

    private final List<Permissions> permissions = new ArrayList<>();

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

    public List<Permissions> getPermissions() {
        return permissions;
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
        private UserSession userSession;

        public Builder builder() {
            this.userSession = new UserSession();
            return this;
        }

        public Builder accessToken(String accessToken) {
            this.userSession.accessToken = accessToken;
            return this;
        }

        public Builder emailConfirmed(boolean emailConfirmed) {
            this.userSession.emailConfirmed = emailConfirmed;
            return this;
        }

        public Builder name(String name) {
            this.userSession.name = name;
            return this;
        }

        public Builder setPermissions(List<Permissions> permissions) {
            this.userSession.permissions.addAll(permissions);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.userSession.createdAt = createdAt;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.userSession.expiresAt = expiresAt;
            return this;
        }

        public UserSession build() {
            return this.userSession;
        }
    }
}
