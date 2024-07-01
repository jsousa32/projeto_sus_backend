package api.projeto_sus_backend.generic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * The Class Generic
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@MappedSuperclass
public abstract class GenericSchema {

    @Column(name = "created_at", length = 25, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", length = 25)
    private LocalDateTime updatedAt;

    @Column(name = "disabled_at", length = 25)
    private LocalDateTime disabledAt;

    private boolean disabled;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(LocalDateTime disabledAt) {
        this.disabledAt = disabledAt;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}