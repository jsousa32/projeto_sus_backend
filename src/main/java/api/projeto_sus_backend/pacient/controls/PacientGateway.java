package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.pacient.entities.PacientSchema;
import api.projeto_sus_backend.user.controls.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class PacientGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Component
public class PacientGateway {

    private final UserRepository<PacientSchema> userRepository;

    private final PacientRepository pacientRepository;

    public PacientGateway(UserRepository<PacientSchema> userRepository,
                          PacientRepository pacientRepository) {
        this.userRepository = userRepository;
        this.pacientRepository = pacientRepository;
    }

    public void existsByEmail(String email) {
        userRepository.findByEmail(email).ifPresent((pacient) -> {
            throw new RuntimeException("Email já cadastrado");
        });
    }

    public void existsByDocument(String document) {
        userRepository.findByDocument(document).ifPresent((pacient) -> {
            throw new RuntimeException("CPF já cadastrado");
        });
    }

    public void existsBySusNumber(String susNumber) {
        pacientRepository.findBySusNumber(susNumber).ifPresent((pacient) -> {
            throw new RuntimeException("Número SUS já cadastrado");
        });
    }

    public void save(Pacient pacient) {
        pacientRepository.save(PacientMapper.convert(pacient));
    }

    public Page<Pacient> findAll(String filter, Pageable pageable) {
        return pacientRepository.findAll(filter, pageable).map(PacientMapper::convert);
    }

    public Pacient findById(UUID id) {
        return pacientRepository.findById(id)
                .map(PacientMapper::convert)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Pacient findByIdResume(UUID id) {
        return pacientRepository.findByIdResume(id).map(PacientMapper::convert)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public void disable(UUID id) {
        pacientRepository.disable(id);
    }
}
