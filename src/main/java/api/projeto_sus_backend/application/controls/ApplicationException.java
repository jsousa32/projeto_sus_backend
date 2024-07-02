package api.projeto_sus_backend.application.controls;

import org.springframework.http.HttpStatus;

/**
 * The Class ApplicationException
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class ApplicationException extends RuntimeException {

    private final String message;

    private final HttpStatus status;

    public ApplicationException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
