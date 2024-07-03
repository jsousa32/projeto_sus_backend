package api.projeto_sus_backend.user.controls;

import api.projeto_sus_backend.user.entities.UserSchema;
import org.springframework.stereotype.Component;

/**
 * The Class UserGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Component
public class UserGateway {

    private final UserRepository<UserSchema> userRepository;

    public UserGateway(UserRepository<UserSchema> userRepository) {
        this.userRepository = userRepository;
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
}
