package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.pacient.entities.PacientSchema;
import api.projeto_sus_backend.user.controls.UserRepository;
import org.springframework.stereotype.Component;

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
}
