package api.projeto_sus_backend.doctor.controls;

import api.projeto_sus_backend.doctor.entities.Doctor;
import api.projeto_sus_backend.doctor.entities.DoctorSchema;
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
 * The Interface DoctorRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@Transactional
@Repository
public interface DoctorRepository extends JpaRepository<DoctorSchema, UUID> {

    Optional<DoctorSchema> findByCrm(@Param("crm") String crm);

    @Query("""
            SELECT d FROM DoctorSchema d
            WHERE d.disabled = false
            AND (
                UPPER(d.firstName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(d.lastName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(d.crm) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(d.email) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(d.document) LIKE UPPER(CONCAT('%', :filter, '%'))
            )
            """)
    Page<DoctorSchema> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("""
            SELECT d FROM DoctorSchema d
            WHERE d.id = :id
            AND d.disabled = false
            """)
    Optional<DoctorSchema> findByIdResume(@Param("id") UUID id);

    @Modifying
    @Query("""
            UPDATE DoctorSchema d
            SET d.disabled = FALSE, d.disabledAt = null, d.updatedAt = CURRENT_TIMESTAMP
            """)
    void active(UUID id);

    @Modifying
    @Query("""
            UPDATE DoctorSchema d
            SET d.disabled = TRUE, d.disabledAt = CURRENT_TIMESTAMP, d.updatedAt = CURRENT_TIMESTAMP
            """)
    void disable(UUID id);
}
