package api.projeto_sus_backend.application.controls;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * The Class ApplicationExceptionsController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@RestControllerAdvice
public class ApplicationExceptionsController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorHandling> handleUserException(ApplicationException exception, HttpServletRequest request) {
        return ResponseEntity.status(exception.getStatus())
                .body(
                        new ErrorHandling(LocalDateTime.now(), exception.getStatus().value(), request.getServletPath(), request.getMethod(),
                                exception.getMessage())
                );
    }

    public record ErrorHandling(LocalDateTime timestamps, Integer statusCode, String path, String method, String message) {
    }
}
