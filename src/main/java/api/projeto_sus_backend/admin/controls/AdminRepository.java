package api.projeto_sus_backend.admin.controls;

import api.projeto_sus_backend.admin.entities.Admin;
import api.projeto_sus_backend.admin.entities.AdminSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * The Interface AdminRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Transactional
@Repository
public interface AdminRepository extends JpaRepository<AdminSchema, UUID> {

    Optional<AdminSchema> findByDocument(@Param("document") String document);

    @Query("""
            SELECT
                a.id as id, a.firstName as firstName, a.lastName as lastName, a.email as email
            FROM AdminSchema a
            """)
    Page<AdminProjections.Page> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("""
            SELECT
                a.id as id, a.firstName as firstName, a.lastName as lastName, a.email as email
            FROM AdminSchema a
            """)
    Optional<AdminSchema> findByIdResume(@Param("id") UUID id);
}
