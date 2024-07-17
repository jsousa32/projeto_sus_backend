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
            SELECT
                p
            FROM PacientSchema p
            WHERE p.disabled = false
            AND (
                UPPER(p.firstName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(p.lastName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(p.susNumber) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(p.email) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(p.document) LIKE UPPER(CONCAT('%', :filter, '%'))
            )
            """)
    Page<PacientProjections.Page> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("""
            SELECT
                p
            FROM PacientSchema p
            WHERE p.id = :id
            AND p.disabled = false
            """)
    Optional<PacientProjections.Resume> findByIdResume(@Param("id") UUID id);

    @Modifying
    @Query("""
            UPDATE PacientSchema p
            SET p.disabled = FALSE, p.disabledAt = null, p.updatedAt = CURRENT_TIMESTAMP
            WHERE p.id = :id
            """)
    void active(UUID id);

    @Modifying
    @Query("""
            UPDATE PacientSchema p
            SET p.disabled = TRUE, p.disabledAt = CURRENT_TIMESTAMP, p.updatedAt = CURRENT_TIMESTAMP
            WHERE p.id = :id
            """)
    void disable(@Param("id") UUID id);
}
