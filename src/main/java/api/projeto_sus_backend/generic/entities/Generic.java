package api.projeto_sus_backend.generic.entities;

import api.projeto_sus_backend.generic.controls.GenericProjections;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * The Class Generic
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@MappedSuperclass
public abstract class Generic {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(GenericProjections.Audit.class)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(GenericProjections.Audit.class)
    private LocalDateTime updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(GenericProjections.Audit.class)
    private LocalDateTime disabledAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(GenericProjections.Audit.class)
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
        this.disabledAt = disabled ? LocalDateTime.now() : null;
    }
}
