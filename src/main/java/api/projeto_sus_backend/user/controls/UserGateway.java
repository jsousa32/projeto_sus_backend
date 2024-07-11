package api.projeto_sus_backend.user.controls;

import api.projeto_sus_backend.admin.controls.AdminGateway;
import api.projeto_sus_backend.application.boundary.AuthExceptions;
import api.projeto_sus_backend.doctor.controls.DoctorGateway;
import api.projeto_sus_backend.pacient.controls.PacientGateway;
import api.projeto_sus_backend.user.entities.User;
import api.projeto_sus_backend.user.entities.UserSchema;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class UserGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Component
public class UserGateway {

    private final UserRepository<UserSchema> userRepository;

    private final PacientGateway pacientGateway;

    private final AdminGateway adminGateway;

    private final DoctorGateway doctorGateway;

    public UserGateway(UserRepository<UserSchema> userRepository,
                       PacientGateway pacientGateway,
                       AdminGateway adminGateway,
                       DoctorGateway doctorGateway) {
        this.userRepository = userRepository;
        this.pacientGateway = pacientGateway;
        this.adminGateway = adminGateway;
        this.doctorGateway = doctorGateway;
    }

    public void existsByEmail(String email) {
        userRepository.findByEmail(email).ifPresent((user) -> {
            throw new UserExceptions.EmailAlreadyUsed();
        });
    }

    public void existsByDocument(String document) {
        userRepository.findByDocument(document).ifPresent((user) -> {
            throw new UserExceptions.DocumentAlreadyUsed();
        });
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::convert).orElseThrow(UserExceptions.NotFound::new);
    }

    public void save(User user) {
        switch (user.getType()) {
            case UserSchema.DESCIMINATOR_DOCTOR:
                doctorGateway.save(user);
                break;
            case UserSchema.DESCIMINATOR_PACIENT:
                pacientGateway.save(user);
                break;
            case UserSchema.DESCRIMINATOR_ADMIN:
                adminGateway.save(user);
                break;
            default:
                throw new AuthExceptions.UserTypeIsNotCorrect();
        }
    }

    public User findById(UUID id) {
        return userRepository.findById(id).map(UserMapper::convert).orElseThrow(UserExceptions.NotFound::new);
    }
}
