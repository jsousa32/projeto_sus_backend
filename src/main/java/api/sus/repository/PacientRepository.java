package api.sus.repository;

import api.sus.model.schema.PacientSchema;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The Interface PacientRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 18/02/2024
 */
@Repository
@Transactional
public interface PacientRepository extends JpaRepository<PacientSchema, UUID> {

    boolean existsBySus(@Param("document") String document);

    boolean existsByEmail(@Param("email") String email);

    PacientSchema findByEmail(@Param("email") String email);
}
