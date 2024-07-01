package api.projeto_sus_backend.pacient.boundary;

import api.projeto_sus_backend.pacient.controls.PacientGateway;
import api.projeto_sus_backend.pacient.entities.Pacient;
import org.springframework.stereotype.Component;

/**
 * The Class PacientBussiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Component
public class PacientBussiness {

    private final PacientGateway pacientGateway;

    public PacientBussiness(PacientGateway pacientGateway) {
        this.pacientGateway = pacientGateway;
    }

    public void save(Pacient pacient) {

        pacientGateway.existsBySusNumber(pacient.getSusNumber());

        pacientGateway.existsByEmail(pacient.getEmail());

        pacientGateway.existsByDocument(pacient.getDocument());


    }
}
