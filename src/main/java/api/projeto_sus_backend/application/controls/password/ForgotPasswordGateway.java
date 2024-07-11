package api.projeto_sus_backend.application.controls.password;

import api.projeto_sus_backend.application.controls.ApplicationMapper;
import api.projeto_sus_backend.application.entities.ForgotPassword;
import api.projeto_sus_backend.application.entities.ForgotPasswordSchema;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class ForgotPasswordGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
@Component
public class ForgotPasswordGateway {

    private final ForgotPasswordRepository forgotPasswordRepository;

    public ForgotPasswordGateway(ForgotPasswordRepository forgotPasswordRepository) {
        this.forgotPasswordRepository = forgotPasswordRepository;
    }

    public void save(ForgotPassword forgotPassword) {
        forgotPasswordRepository.save(ApplicationMapper.convert(forgotPassword));
    }

    public ForgotPassword saveAndReturn(ForgotPassword forgotPassword) {
        return ApplicationMapper.convert(forgotPasswordRepository.save(ApplicationMapper.convert(forgotPassword)));
    }

    public ForgotPassword findByUserId(UUID userId) {
        return forgotPasswordRepository.findByUserId(userId).map(ApplicationMapper::convert)
                .orElseThrow(ForgotPasswordExceptions.ForgotPasswordByUserIdNotFound::new);
    }
}
