package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class PacientExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class PacientExceptions {

    public static class NotFound extends ApplicationException {
        public NotFound() {
            super("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public static class SusNumberAlreadyUsed extends ApplicationException {
        public SusNumberAlreadyUsed() {
            super("Número do SUS já cadastrado", HttpStatus.CONFLICT);
        }
    }
}
