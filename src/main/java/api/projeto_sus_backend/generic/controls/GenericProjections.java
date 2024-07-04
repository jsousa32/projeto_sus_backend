package api.projeto_sus_backend.generic.controls;

import java.time.LocalDateTime;

/**
 * The Class GenericProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class GenericProjections {

    public interface Audit {
        LocalDateTime getCreatedAt();

        LocalDateTime getUpdatedAt();

        LocalDateTime getDisabledAt();

        boolean isDisabled();
    }

    public interface CreatedAt {
        LocalDateTime getCreatedAt();
    }

    public interface UpdatedAt {
        LocalDateTime getUpdatedAt();
    }

    public interface DisabledAt {
        LocalDateTime getDisabledAt();
    }

    public interface IsDisabled {
        boolean isDisabled();
    }
}
