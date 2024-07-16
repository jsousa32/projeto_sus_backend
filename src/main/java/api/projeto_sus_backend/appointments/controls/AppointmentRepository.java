package api.projeto_sus_backend.appointments.controls;

import api.projeto_sus_backend.appointments.entities.AppointmentSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The Interface AppointmentRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@Transactional
@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentSchema, UUID> {

    @Query("""
            SELECT a FROM AppointmentSchema a
            JOIN a.doctor d
            WHERE d.id = :doctorId
            """)
    List<AppointmentSchema> findAllAppointmentsByDoctorId(@Param("doctorId") UUID doctorId);

    @Query("""
            SELECT
                a
            FROM AppointmentSchema a
            WHERE (
                UPPER(a.doctor.firstName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(a.doctor.lastName) LIKE UPPER(CONCAT('%', :filter, '%'))
            )
            """)
    Page<AppointmentProjections.Page> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("""
            SELECT
                a
            FROM AppointmentSchema a
            WHERE a.id = :id
            """)
    Optional<AppointmentProjections.Resume> findByIdResume(@Param("id") UUID id);
}
