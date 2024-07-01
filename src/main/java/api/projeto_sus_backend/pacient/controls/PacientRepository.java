package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.pacient.entities.PacientSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Query("""
            SELECT p FROM PacientSchema p
            WHERE (
                UPPER(p.firstName) LIKE UPPER(CONCAT('%', p.firstName, '%')) OR
                UPPER(p.lastName) LIKE UPPER(CONCAT('%', p.lastName, '%')) OR
                UPPER(p.susNumber) LIKE UPPER(CONCAT('%', p.susNumber, '%')) OR
                UPPER(p.email) LIKE UPPER(CONCAT('%', p.email, '%')) OR
                UPPER(p.document) LIKE UPPER(CONCAT('%', p.document, '%'))
            )
            """)
    Page<PacientProjections.PacientPageProjection> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("""
            SELECT p FROM PacientSchema p
            WHERE p.id = :id
            """)
    Optional<PacientProjections.PacientResumeProjection> findByIdResume(@Param("id") UUID id);

    @Modifying
    @Query("""
            UPDATE PacientSchema p
            SET p.disabled = TRUE, p.disabledAt = CURRENT_TIMESTAMP
            WHERE p.id = :id
            """)
    void disable(@Param("id") UUID id);
}