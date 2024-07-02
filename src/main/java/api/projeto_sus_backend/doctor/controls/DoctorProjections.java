package api.projeto_sus_backend.doctor.controls;

import api.projeto_sus_backend.user.controls.UserProjections;

/**
 * The Class DoctorProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class DoctorProjections {

    public interface Page extends
            UserProjections.Id, UserProjections.FirstName, UserProjections.LastName, UserProjections.Email, UserProjections.Telephone {

        String getCrm();
    }

    public interface Resume extends
            UserProjections.Id, UserProjections.FirstName, UserProjections.LastName, UserProjections.Email, UserProjections.Telephone,
            UserProjections.Document {

        String getCrm();
    }
}
