package api.sus.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class Generic
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Data
public abstract class Generic {

    private UUID id;

    private boolean disabled;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDateTime disabledAt;


    public void setDisabled(boolean disabled) {
        if (disabled) this.disabledAt = LocalDateTime.now();
        else this.disabledAt = null;

        this.disabled = true;
        this.updatedAt = LocalDateTime.now();
    }
}
