package api.projeto_sus_backend.application.entities;

import api.projeto_sus_backend.generic.entities.GenericSchema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class ForgotPasswordSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
@Entity
@Table(name = "tb_forgot_password", indexes = {
        @Index(columnList = "userId")
})
public class ForgotPasswordSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false, length = 150)
    private UUID userId;

    @Column(name = "expires_at", nullable = false, length = 30)
    private LocalDateTime expiresAt;

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
     * The Builder of ForgotPasswordSchema
     */
    public static class Builder {
        private ForgotPasswordSchema forgotPasswordSchema;

        public Builder builder() {
            this.forgotPasswordSchema = new ForgotPasswordSchema();
            return this;
        }

        public Builder setId(UUID id) {
            this.forgotPasswordSchema.setId(id);
            return this;
        }

        public Builder setUserId(UUID userId) {
            this.forgotPasswordSchema.setUserId(userId);
            return this;
        }

        public Builder setExpiresAt(LocalDateTime expiresAt) {
            this.forgotPasswordSchema.setExpiresAt(expiresAt);
            return this;
        }

        public ForgotPasswordSchema build() {
            return this.forgotPasswordSchema;
        }
    }
}
