package api.projeto_sus_backend.user.controls;

import api.projeto_sus_backend.user.entities.enums.Permissions;

import java.util.List;
import java.util.UUID;

/**
 * The Class UserProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class UserProjections {

    public interface Page extends UserProjections.Id, UserProjections.FirstName, UserProjections.LastName, UserProjections.Email,
            UserProjections.Telephone, UserProjections.Document {
    }

    public interface Resume extends UserProjections.Page {
    }

    public interface Create extends UserProjections.Resume, UserProjections.Password {
    }

    public interface CreateWithoutPassword extends UserProjections.Resume {
    }

    public interface EditablesFields extends UserProjections.FirstName, UserProjections.LastName, UserProjections.Email,
            UserProjections.Telephone {
    }

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

    public interface Permission {
        List<Permissions> getPermissions();
    }
}
