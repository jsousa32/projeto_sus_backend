package api.projeto_sus_backend.doctor.controls;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class DoctorExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class DoctorExceptions {

    public static class NotFound extends ApplicationException {
        public NotFound() {
            super("Médico não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public static class CrmAlreadyUsed extends ApplicationException {
        public CrmAlreadyUsed() {
            super("Número do CRM já cadastrado", HttpStatus.CONFLICT);
        }
    }
}
