package api.projeto_sus_backend.application.controls.mail;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class MailExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 09/07/2024
 */
public class MailExceptions {

    public static class ErrorToSend extends ApplicationException {
        public ErrorToSend() {
            super("Erro ao enviar o e-mail", HttpStatus.BAD_REQUEST);
        }
    }

    public static class ErrorToConvertTemplate extends ApplicationException {
        public ErrorToConvertTemplate() {
            super("Erro ao converter o template de e-mail", HttpStatus.BAD_REQUEST);
        }
    }

    public static class InvalidDestination extends ApplicationException {
        public InvalidDestination() {
            super("Ao menos um destinatário é obrigatório", HttpStatus.BAD_REQUEST);
        }
    }

    public static class InvalidSubject extends ApplicationException {
        public InvalidSubject() {
            super("Assunto do e-mail é obrigatório", HttpStatus.BAD_REQUEST);
        }
    }
}
