package api.projeto_sus_backend.user.controls;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class UserExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class UserExceptions {

    public static class EmailAlreadyUsed extends ApplicationException {
        public EmailAlreadyUsed() {
            super("Email já cadastrado", HttpStatus.CONFLICT);
        }
    }

    public static class DocumentAlreadyUsed extends ApplicationException {
        public DocumentAlreadyUsed() {
            super("Documento já cadastrado", HttpStatus.CONFLICT);
        }
    }
}
