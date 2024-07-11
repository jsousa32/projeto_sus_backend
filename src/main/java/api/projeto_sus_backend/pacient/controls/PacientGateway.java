package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.user.entities.User;
import org.springframework.beans.BeanUtils;
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

    private final PacientRepository pacientRepository;

    public PacientGateway(PacientRepository pacientRepository) {
        this.pacientRepository = pacientRepository;
    }

    public void existsBySusNumber(String susNumber) {
        pacientRepository.findBySusNumber(susNumber).ifPresent((pacient) -> {
            throw new PacientExceptions.SusNumberAlreadyUsed();
        });
    }

    public void save(Pacient pacient) {
        pacientRepository.save(PacientMapper.convert(pacient));
    }

    public void save(User user) {
        Pacient pacient = this.findById(user.getId());

        BeanUtils.copyProperties(user, pacient, "id", "susNumber");

        this.save(pacient);
    }

    public Page<Pacient> findAll(String filter, Pageable pageable) {
        return pacientRepository.findAll(filter, pageable).map(PacientMapper::convert);
    }

    public Pacient findById(UUID id) {
        return pacientRepository.findById(id)
                .map(PacientMapper::convert)
                .orElseThrow(PacientExceptions.NotFound::new);
    }

    public Pacient findByIdResume(UUID id) {
        return pacientRepository.findByIdResume(id).map(PacientMapper::convert)
                .orElseThrow(PacientExceptions.NotFound::new);
    }

    public Pacient findByAppointmentId(UUID id) {
        return pacientRepository.findByAppointmentId(id).map(PacientMapper::convert)
                .orElseThrow(PacientExceptions.NotFound::new);
    }

    public void active(UUID id) {
        pacientRepository.active(id);
    }

    public void disable(UUID id) {
        pacientRepository.disable(id);
    }
}
