package api.projeto_sus_backend.application.controls.password;

import api.projeto_sus_backend.application.entities.ForgotPasswordSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The Interface ForgotPasswordRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
@Transactional
@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordSchema, UUID> {
}
