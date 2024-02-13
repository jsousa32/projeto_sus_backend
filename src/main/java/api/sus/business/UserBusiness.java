package api.sus.business;

import api.sus.exception.GlobalException;
import api.sus.gateway.UserGateway;
import api.sus.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * The Class UserBusiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Component
public class UserBusiness {

    private final UserGateway userGateway;

    @Autowired
    public UserBusiness(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User save(User user) {
        if (userGateway.existsByDocument(user.getDocument())) {
            throw new GlobalException("Documento já cadastrado", HttpStatus.CONFLICT);
        }

        if (userGateway.existsByEmail(user.getEmail())) {
            throw new GlobalException("E-mail já cadastrado", HttpStatus.CONFLICT);
        }

        user.encryptPassword();

        return userGateway.saveAndReturn(user);
    }
}
