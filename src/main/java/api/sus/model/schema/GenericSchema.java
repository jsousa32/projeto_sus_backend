package api.sus.model.schema;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class GenericSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean disabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime disabledAt;
}
