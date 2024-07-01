package api.projeto_sus_backend.user.enums;

/**
 * The Enum Discriminator
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public enum Discriminator {
    PACIENT("Pacient"),
    DOCTOR("Doctor");

    private final String label;


    Discriminator(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
