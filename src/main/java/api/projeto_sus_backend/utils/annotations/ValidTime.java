package api.projeto_sus_backend.utils.annotations;

import api.projeto_sus_backend.utils.TimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Annotation ValidTime
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@Constraint(validatedBy = TimeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTime {

    String message() default "Horário inválido. Deve estar entre 08:00 e 19:00 em horas cheias.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
