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

    public static class UserIdInvalid extends ApplicationException {
        public UserIdInvalid() {
            super("O usuário vinculado está inválido", HttpStatus.BAD_REQUEST);
        }
    }

    public static class UrlExpired extends ApplicationException {
        public UrlExpired() {
            super("A url para redefinição de senha está expirada", HttpStatus.BAD_REQUEST);
        }
    }
}
