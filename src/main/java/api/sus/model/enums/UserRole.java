package api.sus.model.enums;

import lombok.Getter;

/**
 * The Enum UserRole
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Getter
public enum UserRole {

    PACIENT("Paciente"),
    DOCTOR("Médico"),
    ADMIN("Administrador");

    private final String label;

    UserRole(String label) {
        this.label = label;
    }
}
