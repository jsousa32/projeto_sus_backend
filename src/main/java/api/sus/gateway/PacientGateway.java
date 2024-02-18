package api.sus.gateway;

import api.sus.model.dto.Pacient;
import api.sus.model.schema.PacientSchema;
import api.sus.repository.PacientRepository;
import api.sus.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class UserGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Component
public class PacientGateway {

    private final Mapper mapper = new Mapper();

    private final PacientRepository pacientRepository;

    @Autowired
    public PacientGateway(PacientRepository pacientRepository) {
        this.pacientRepository = pacientRepository;
    }

    public boolean existsBySus(String document) {
        return pacientRepository.existsBySus(document);
    }

    public boolean existsByEmail(String email) {
        return pacientRepository.existsByEmail(email);
    }

    public void save(Pacient pacient) {
        pacientRepository.save(mapper.converter(pacient, PacientSchema.class));
    }

    public Pacient saveAndReturn(Pacient pacient) {
        PacientSchema response = pacientRepository.save(mapper.converter(pacient, PacientSchema.class));
        return mapper.converter(response, Pacient.class);
    }

    public Pacient findByEmail(String email) {
        PacientSchema response = pacientRepository.findByEmail(email);
        return mapper.converter(response, Pacient.class);
    }
}
