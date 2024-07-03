package api.projeto_sus_backend.user.controls;

import java.util.UUID;

/**
 * The Class UserProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class UserProjections {

    public interface Id {
        UUID getId();
    }

    public interface FirstName {
        String getFirstName();
    }

    public interface LastName {
        String getLastName();
    }

    public interface Email {
        String getEmail();
    }

    public interface Telephone {
        String getTelephone();
    }

    public interface Password {
        String getPassword();
    }

    public interface Document {
        String getDocument();
    }
}
