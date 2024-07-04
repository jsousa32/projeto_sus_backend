package api.projeto_sus_backend.application.controls;

import api.projeto_sus_backend.generic.controls.GenericProjections;
import api.projeto_sus_backend.user.controls.UserProjections;

/**
 * The Class ApplicationProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class ApplicationProjections {

    public interface UserDetails extends UserProjections.Id, GenericProjections.IsDisabled, UserProjections.Email, UserProjections.Password,
            UserProjections.Permission {
    }
}
