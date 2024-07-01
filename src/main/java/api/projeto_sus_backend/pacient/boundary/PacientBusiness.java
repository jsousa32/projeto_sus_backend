package api.projeto_sus_backend.pacient.boundary;

import api.projeto_sus_backend.pacient.controls.PacientGateway;
import api.projeto_sus_backend.pacient.entities.Pacient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class PacientBussiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Component
public class PacientBusiness {

    private final PacientGateway pacientGateway;

    public PacientBusiness(PacientGateway pacientGateway) {
        this.pacientGateway = pacientGateway;
    }

    public void save(Pacient pacient) {

        pacientGateway.existsBySusNumber(pacient.getSusNumber());

        pacientGateway.existsByEmail(pacient.getEmail());

        pacientGateway.existsByDocument(pacient.getDocument());

        pacientGateway.save(pacient);
    }

    public Page<Pacient> findAll(String filter, Pageable pageable) {
        return pacientGateway.findAll(filter, pageable);
    }

    public Pacient findById(UUID id) {
        return pacientGateway.findByIdResume(id);
    }

    public void update(UUID id, Pacient pacient) {
        pacientGateway.existsByEmail(pacient.getEmail());

        Pacient pacientDB = pacientGateway.findById(id);

        BeanUtils.copyProperties(pacientDB, pacient, "id, susNumber, document");

        pacientGateway.save(pacient);
    }

    public void disable(UUID id) {
        pacientGateway.disable(id);
    }
}
