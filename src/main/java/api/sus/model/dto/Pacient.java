package api.sus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Class Pacient
 *
 * @author João Lucas Silva de Sousa
 * @sincer 18/02/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pacient extends User {

    @Pattern(regexp = "\\d{6}|\\d{15}", message = "O documento deve possuir apenas 6 ou 15 caracteres númericos, referente ao CRM e ao Número SUS")
    private String sus;

}
