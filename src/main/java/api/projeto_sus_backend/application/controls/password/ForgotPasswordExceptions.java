package api.projeto_sus_backend.application.controls.password;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class ForgotPasswordExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
public class ForgotPasswordExceptions {

    public static class ForgotPasswordByUserIdNotFound extends ApplicationException {
        public ForgotPasswordByUserIdNotFound() {
            super("Não foi possível encontrar usuário vinculado a essa redefinição de senha", HttpStatus.NOT_FOUND);
        }
    }
}
