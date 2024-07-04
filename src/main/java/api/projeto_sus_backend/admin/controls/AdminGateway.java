package api.projeto_sus_backend.admin.controls;

import api.projeto_sus_backend.admin.entities.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class AdminGateway
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Component
public class AdminGateway {

    private final AdminRepository adminRepository;

    public AdminGateway(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void save(Admin admin) {
        adminRepository.save(AdminMapper.convert(admin));
    }

    public void existsByDocument(String document) {
        adminRepository.findByDocument(document).ifPresent(admin -> {
            throw new AdminExceptions.DocumentAlreadyUsed();
        });
    }

    public Page<Admin> findAll(String filter, Pageable pageable) {
        return adminRepository.findAll(filter, pageable).map(AdminMapper::convert);
    }

    public Admin findByIdResume(UUID id) {
        return adminRepository.findByIdResume(id).map(AdminMapper::convert).orElseThrow(AdminExceptions.NotFound::new);
    }

    public Admin findById(UUID id) {
        return adminRepository.findById(id).map(AdminMapper::convert).orElseThrow(AdminExceptions.NotFound::new);
    }

    public void delete(UUID id) {
        adminRepository.deleteById(id);
    }
}
