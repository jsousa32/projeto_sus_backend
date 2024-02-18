package api.sus.gateway;

import api.sus.model.dto.Pacient;
import api.sus.model.dto.User;
import api.sus.model.schema.PacientSchema;
import api.sus.model.schema.UserSchema;
import api.sus.repository.UserRepository;
import api.sus.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class UserGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 18/02/2024
 */
@Component
public class UserGateway {

    private final Mapper mapper = new Mapper();

    private final UserRepository userRepository;

    @Autowired
    public UserGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        UserSchema response = userRepository.findByEmail(email);

        if(response instanceof PacientSchema) {
            return mapper.converter(response, Pacient.class);
        }

        return mapper.converter(response, User.class);
    }

    public void save(User user) {
        userRepository.save(mapper.converter(user, UserSchema.class));
    }
}
