package api.sus.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * The Class GlobalException
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Getter
public class GlobalException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -656344035877902535L;

    private final String message;

    private final HttpStatus httpStatus;

    public GlobalException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
