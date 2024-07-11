package api.projeto_sus_backend.admin.boundary;

import api.projeto_sus_backend.admin.controls.AdminGateway;
import api.projeto_sus_backend.admin.entities.Admin;
import api.projeto_sus_backend.application.controls.mail.MailService;
import api.projeto_sus_backend.user.controls.UserGateway;
import api.projeto_sus_backend.user.entities.enums.Permissions;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class AdminBusiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Component
public class AdminBusiness {

    private final AdminGateway adminGateway;

    private final UserGateway userGateway;

    private final MailService mailService;

    public AdminBusiness(AdminGateway adminGateway,
                         UserGateway userGateway,
                         MailService mailService) {
        this.adminGateway = adminGateway;
        this.userGateway = userGateway;
        this.mailService = mailService;
    }

    /**
     * Metodo responsável por salvar um administrador
     *
     * @param admin;
     */
    public void save(Admin admin) {

        userGateway.existsByEmail(admin.getEmail());

        adminGateway.existsByDocument(admin.getDocument());

        admin.getPermissions().add(Permissions.ADMIN);

        admin = adminGateway.save(admin);

        mailService.emailConfirmation(admin);

        mailService.registerPassword(admin);
    }

    /**
     * Metodo responsável por retornar todos os administradores do sistema
     *
     * @param filter;
     * @param pageable;
     * @return Page<Admin>;
     */
    public Page<Admin> findAll(String filter, Pageable pageable) {
        return adminGateway.findAll(filter, pageable);
    }

    /**
     * Metodo responsável por retornar um administrador do sistema
     *
     * @param id;
     * @return Admin;
     */
    public Admin findById(UUID id) {
        return adminGateway.findByIdResume(id);
    }

    /**
     * Metodo responsável por atualizar um administrador
     *
     * @param id;
     * @param admin;
     */
    public void update(UUID id, Admin admin) {
        Admin adminDB = adminGateway.findById(id);

        if (!adminDB.getEmail().equals(admin.getEmail())) {
            userGateway.existsByEmail(admin.getEmail());
        }

        BeanUtils.copyProperties(admin, adminDB, "id", "document", "password");

        adminGateway.save(adminDB);
    }

    /**
     * Metodo responsável por deletar um administrador
     *
     * @param id;
     */
    public void delete(UUID id) {
        adminGateway.delete(id);
    }
}
