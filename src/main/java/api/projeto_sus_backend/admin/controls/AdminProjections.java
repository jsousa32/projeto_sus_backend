package api.projeto_sus_backend.admin.controls;

import api.projeto_sus_backend.user.controls.UserProjections;

/**
 * The Class AdminProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class AdminProjections {

    public interface Page extends UserProjections.Page {
    }

    public interface Resume extends UserProjections.Resume {
    }

    public interface Create extends UserProjections.CreateWithoutPassword {
    }

    public interface EditablesFields extends UserProjections.EditablesFields {
    }
}
