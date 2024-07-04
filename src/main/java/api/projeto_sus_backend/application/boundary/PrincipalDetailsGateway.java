package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.controls.ApplicationMapper;
import api.projeto_sus_backend.user.controls.UserExceptions;
import api.projeto_sus_backend.user.controls.UserRepository;
import api.projeto_sus_backend.user.entities.UserSchema;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * The Class PrincipalDetailsGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
@Component
public class PrincipalDetailsGateway implements UserDetailsService {

    private final UserRepository<UserSchema> userRepository;

    public PrincipalDetailsGateway(UserRepository<UserSchema> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.loadUserByUsername(email).map(ApplicationMapper::convert).orElseThrow(UserExceptions.NotFound::new);
    }
}
