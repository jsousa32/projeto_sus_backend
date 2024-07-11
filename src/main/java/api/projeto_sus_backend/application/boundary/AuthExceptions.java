package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class AuthExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
public class AuthExceptions {

    public static class CodeConfirmationEmailNotValid extends ApplicationException {
        public CodeConfirmationEmailNotValid() {
            super("Código de confirmação de email inválido", HttpStatus.BAD_REQUEST);
        }
    }

    public static class EmailAlreadyConfirmed extends ApplicationException {
        public EmailAlreadyConfirmed() {
            super("O email do usuário já está confirmado", HttpStatus.CONFLICT);
        }
    }

    public static class UserTypeIsNotCorrect extends ApplicationException {
        public UserTypeIsNotCorrect() {
            super("O tipo do usuário está incorreto", HttpStatus.BAD_REQUEST);
        }
    }
}
