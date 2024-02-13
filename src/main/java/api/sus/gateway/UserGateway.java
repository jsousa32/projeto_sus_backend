package api.sus.gateway;

import api.sus.model.dto.User;
import api.sus.model.schema.UserSchema;
import api.sus.repository.UserRepository;
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
public class UserGateway {

    private final Mapper mapper = new Mapper();

    private final UserRepository userRepository;

    @Autowired
    public UserGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByDocument(String document) {
        return userRepository.existsByDocument(document);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        userRepository.save(mapper.converter(user, UserSchema.class));
    }

    public User saveAndReturn(User user) {
        UserSchema response = userRepository.save(mapper.converter(user, UserSchema.class));
        return mapper.converter(response, User.class);
    }
}
