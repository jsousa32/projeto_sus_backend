package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.user.controls.UserProjections;

/**
 * The Class PacientProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class PacientProjections {

    public interface Page extends
            UserProjections.Id, UserProjections.FirstName, UserProjections.LastName, UserProjections.Email, UserProjections.Telephone {

        String getSusNumber();
    }

    public interface Resume extends
            UserProjections.Id, UserProjections.FirstName, UserProjections.LastName, UserProjections.Email, UserProjections.Telephone,
            UserProjections.Document {

        String getSusNumber();
    }
}
