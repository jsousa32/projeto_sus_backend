package api.projeto_sus_backend.admin.controls;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class AdminExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class AdminExceptions {

    public static class DocumentAlreadyUsed extends ApplicationException {
        public DocumentAlreadyUsed() {
            super("Documento já cadastrado", HttpStatus.CONFLICT);
        }
    }

    public static class NotFound extends ApplicationException {
        public NotFound() {
            super("Administrador não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public static class NeedOneAdmin extends ApplicationException {
        public NeedOneAdmin() {
            super("O sistema necessita de ao menos um administrador", HttpStatus.BAD_REQUEST);
        }
    }
}
