package api.sus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class Generic
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Generic {

    private UUID id;

    private boolean disabled = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDateTime disabledAt;


    public void disabled(boolean disabled) {
        if (disabled) this.disabledAt = LocalDateTime.now();
        else this.disabledAt = null;

        this.disabled = disabled;
        this.updatedAt = LocalDateTime.now();
    }
}
