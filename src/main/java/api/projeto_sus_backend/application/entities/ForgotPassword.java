package api.projeto_sus_backend.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class ForgotPassword
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
public class ForgotPassword {

    private UUID id;

    private UUID userId;

    private LocalDateTime expiresAt = LocalDateTime.now().plusHours(2);

    public ForgotPassword() {
    }

    public ForgotPassword(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * The Builder of ForgotPassword
     */
    public static class Builder {
        private ForgotPassword forgotPassword;

        public Builder builder() {
            this.forgotPassword = new ForgotPassword();
            return this;
        }

        public Builder setId(UUID id) {
            this.forgotPassword.setId(id);
            return this;
        }

        public Builder setUserId(UUID userId) {
            this.forgotPassword.setUserId(userId);
            return this;
        }

        public Builder setExpiresAt(LocalDateTime expiresAt) {
            this.forgotPassword.setExpiresAt(expiresAt);
            return this;
        }

        public ForgotPassword build() {
            return this.forgotPassword;
        }
    }
}
