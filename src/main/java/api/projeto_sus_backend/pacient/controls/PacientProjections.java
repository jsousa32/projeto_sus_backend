package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.appointments.controls.AppointmentProjections;
import api.projeto_sus_backend.user.controls.UserProjections;

import java.util.List;

/**
 * The Class PacientProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class PacientProjections {

    public interface Page extends UserProjections.Page {

        String getSusNumber();
    }

    public interface Resume extends UserProjections.Resume {

        String getSusNumber();
    }

    public interface Create extends UserProjections.Create {
    }

    public interface CreateInternal extends UserProjections.CreateWithoutPassword {}

    public interface EditablesFields extends UserProjections.EditablesFields {
    }

    public interface ResumeToAppointment extends UserProjections.Id, UserProjections.FirstName, UserProjections.LastName {

        String getSusNumber();
    }
}
