package api.projeto_sus_backend.doctor.controls;

import api.projeto_sus_backend.user.controls.UserProjections;

/**
 * The Class DoctorProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class DoctorProjections {

    public interface Page extends UserProjections.Page {

        String getCrm();
    }

    public interface Resume extends UserProjections.Resume {

        String getCrm();
    }

    public interface Create extends UserProjections.CreateWithoutPassword {}

    public interface EditablesFields extends UserProjections.EditablesFields {}

    public interface ResumeToAppointments extends UserProjections.Id, UserProjections.FirstName, UserProjections.LastName {
        String getCrm();
    }
}
