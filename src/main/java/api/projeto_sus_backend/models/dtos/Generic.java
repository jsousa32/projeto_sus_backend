package api.projeto_sus_backend.models.dtos;

import java.time.LocalDateTime;

/**
 * The Class Generic
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public abstract class Generic {

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public LocalDateTime disabledAt;

    private boolean disabled = false;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void isDisabled(boolean disabled) {
        this.disabled = disabled;
        this.updatedAt = LocalDateTime.now();
        this.disabledAt = disabled ? LocalDateTime.now() : null;
    }
}
