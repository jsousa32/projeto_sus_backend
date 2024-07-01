package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.pacient.entities.PacientSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * The Interface PacientRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Transactional
@Repository
public interface PacientRepository extends JpaRepository<PacientSchema, UUID> {

    Optional<PacientSchema> findBySusNumber(@Param("susNumber") String susNumber);
}
