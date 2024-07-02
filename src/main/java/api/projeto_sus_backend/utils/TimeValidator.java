package api.projeto_sus_backend.utils;

import api.projeto_sus_backend.utils.annotations.ValidTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The Class TimeValidator
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
public class TimeValidator implements ConstraintValidator<ValidTime, String> {
    private static final String TIME_PATTERN = "^(0[8-9]|1[0-9]):00$";

    private static final Pattern pattern = Pattern.compile(TIME_PATTERN);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (Objects.isNull(value) || value.isBlank()) {
            return false;
        }

        return pattern.matcher(value).matches();
    }
}
