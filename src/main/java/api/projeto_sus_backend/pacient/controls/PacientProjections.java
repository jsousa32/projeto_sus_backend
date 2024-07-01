package api.projeto_sus_backend.pacient.controls;

import java.util.UUID;

/**
 * The Class PacientProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class PacientProjections {

    public interface PacientPageProjection {

        UUID getId();
        String getFirstName();
        String getLastName();
        String getSusNumber();
        String getEmail();
        String getTelephone();
    }

    public interface PacientResumeProjection {
        UUID getId();
        String getFirstName();
        String getLastName();
        String getSusNumber();
        String getEmail();
        String getTelephone();
        String getDocument();
    }
}
