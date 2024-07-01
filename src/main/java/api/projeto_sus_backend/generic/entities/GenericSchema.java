package api.projeto_sus_backend.generic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * The Class Generic
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class GenericSchema {

    @Column(name = "created_at", length = 25, nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", length = 25)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "disabled_at", length = 25)
    private LocalDateTime disabledAt;

    private boolean disabled;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
