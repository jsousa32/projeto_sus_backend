package api.projeto_sus_backend.doctor.controls;

import api.projeto_sus_backend.doctor.entities.Doctor;
import api.projeto_sus_backend.doctor.entities.DoctorSchema;
import api.projeto_sus_backend.user.controls.UserExceptions;
import api.projeto_sus_backend.user.controls.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class DoctorGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@Component
public class DoctorGateway {

    private final DoctorRepository doctorRepository;

    private final UserRepository<DoctorSchema> userRepository;

    public DoctorGateway(DoctorRepository doctorRepository, UserRepository<DoctorSchema> userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public void existsByCrm(String crm) {
        doctorRepository.findByCrm(crm).ifPresent(doctor -> {
            throw new DoctorExceptions.CrmAlreadyUsed();
        });
    }

    public void existsByEmail(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserExceptions.EmailAlreadyUsed();
        });
    }

    public void existsByDocument(String document) {
        userRepository.findByDocument(document).ifPresent(user -> {
            throw new UserExceptions.DocumentAlreadyUsed();
        });
    }

    public void save(Doctor doctor) {
        doctorRepository.save(DoctorMapper.convert(doctor));
    }

    public Page<Doctor> findAll(String filter, Pageable pageable) {
        return doctorRepository.findAll(filter, pageable).map(DoctorMapper::convert);
    }

    public Doctor findByIdResume(UUID id) {
        return doctorRepository.findByIdResume(id).map(DoctorMapper::convert)
                .orElseThrow(DoctorExceptions.NotFound::new);
    }

    public Doctor findById(UUID id) {
        return doctorRepository.findById(id).map(DoctorMapper::convert)
                .orElseThrow(DoctorExceptions.NotFound::new);
    }

    public void active(UUID id) {
        doctorRepository.active(id);
    }

    public void disable(UUID id) {
        doctorRepository.disable(id);
    }
}
