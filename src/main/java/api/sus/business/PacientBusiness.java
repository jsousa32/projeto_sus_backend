package api.sus.business;

import api.sus.exception.GlobalException;
import api.sus.gateway.PacientGateway;
import api.sus.model.dto.Pacient;
import api.sus.service.mail.JavaMailService;
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
public class PacientBusiness {

    private final PacientGateway pacientGateway;

    private final JavaMailService javaMailService;

    @Autowired
    public PacientBusiness(PacientGateway pacientGateway,
                           JavaMailService javaMailService) {
        this.pacientGateway = pacientGateway;
        this.javaMailService = javaMailService;
    }

    public Pacient save(Pacient pacient) {
        if (pacientGateway.existsBySus(pacient.getSus())) {
            throw new GlobalException("Documento do SUS já cadastrado", HttpStatus.CONFLICT);
        }

        if (pacientGateway.existsByEmail(pacient.getEmail())) {
            throw new GlobalException("E-mail já cadastrado", HttpStatus.CONFLICT);
        }

        pacient.encryptPassword();

        pacient = pacientGateway.saveAndReturn(pacient);

        javaMailService.senderEmailToken(pacient);

        return pacient;
    }
}
