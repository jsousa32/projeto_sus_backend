package api.projeto_sus_backend.pacient.boundary;

import api.projeto_sus_backend.application.controls.ApplicationException;
import api.projeto_sus_backend.application.controls.mail.MailService;
import api.projeto_sus_backend.pacient.controls.PacientGateway;
import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.user.controls.UserGateway;
import api.projeto_sus_backend.user.entities.enums.Permissions;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The Class PacientBussiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Component
public class PacientBusiness {

    private final PacientGateway pacientGateway;

    private final UserGateway userGateway;

    private final MailService mailService;

    public PacientBusiness(PacientGateway pacientGateway,
                           UserGateway userGateway, MailService mailService) {
        this.pacientGateway = pacientGateway;
        this.userGateway = userGateway;
        this.mailService = mailService;
    }

    /**
     * Metodo responsável por criar um paciente
     *
     * @param pacient;
     */
    @Transactional(rollbackFor = ApplicationException.class)
    public void save(Pacient pacient) {

        pacientGateway.existsBySusNumber(pacient.getSusNumber());

        userGateway.existsByEmail(pacient.getEmail());

        userGateway.existsByDocument(pacient.getDocument());

        pacient.encryptPassword();

        pacient.getPermissions().add(Permissions.PACIENT);

        pacient = pacientGateway.save(pacient);

        mailService.emailConfirmation(pacient);
    }

    /**
     * Metodo responsável por buscar todos os pacientes
     *
     * @param filter;
     * @param pageable;
     * @return Page<Pacient>;
     */
    public Page<Pacient> findAll(String filter, Pageable pageable) {
        return pacientGateway.findAll(filter, pageable);
    }

    /**
     * Metodo responsável por buscar um paciente
     *
     * @param id;
     * @return Pacient;
     */
    public Pacient findById(UUID id) {
        return pacientGateway.findByIdResume(id);
    }

    /**
     * Metodo responsável por atualizar um paciente
     *
     * @param id;
     * @param pacient;
     */
    public void update(UUID id, Pacient pacient) {
        Pacient pacientDB = pacientGateway.findById(id);

        if (!pacientDB.getEmail().equals(pacient.getEmail())) {
            userGateway.existsByEmail(pacient.getEmail());
        }

        BeanUtils.copyProperties(pacient, pacientDB, "id", "susNumber", "document", "password");

        pacientGateway.save(pacientDB);
    }


    /**
     * Metodo responsável por reativar um paciente
     *
     * @param id;
     */
    public void active(UUID id) {
        pacientGateway.active(id);
    }

    /**
     * Metodo responsável por desativar um paciente
     *
     * @param id;
     */
    public void disable(UUID id) {
        pacientGateway.disable(id);
    }
}
