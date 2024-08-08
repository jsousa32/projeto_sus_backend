package api.projeto_sus_backend.appointments.controls;

import api.projeto_sus_backend.appointments.entities.AppointmentSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
            SELECT a FROM AppointmentSchema a
            JOIN a.doctor d
            JOIN a.pacient p
            WHERE d.id = :doctorId
            AND p.disabled = false
            AND (
                UPPER(a.pacient.firstName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(a.pacient.lastName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(CONCAT(a.pacient.firstName, ' ', a.pacient.lastName)) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                TO_CHAR(a.date, 'DD/MM/YYYY') LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(a.hour) LIKE UPPER(CONCAT('%', :filter, '%'))
            )
            """)
    Page<AppointmentSchema> findAllAppointmentsByDoctorId(@Param("doctorId") UUID doctorId, @Param("filter") String filter, Pageable pageable);

    @Query("""
            SELECT
                a
            FROM AppointmentSchema a
            JOIN a.pacient p
            JOIN a.doctor d
            WHERE d.disabled = false AND p.disabled = false
            AND (
                UPPER(a.doctor.firstName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(a.doctor.lastName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(CONCAT(a.doctor.firstName, ' ', a.doctor.lastName)) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(a.pacient.firstName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(a.pacient.lastName) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(CONCAT(a.pacient.firstName, ' ', a.pacient.lastName)) LIKE UPPER(CONCAT('%', :filter, '%')) OR
                TO_CHAR(a.date, 'DD/MM/YYYY') LIKE UPPER(CONCAT('%', :filter, '%')) OR
                UPPER(a.hour) LIKE UPPER(CONCAT('%', :filter, '%'))
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

    List<AppointmentSchema> findByDoctorIdAndDate(@Param("doctorId") UUID doctorId, @Param("dateAppointment") LocalDate dateAppointment);

    List<AppointmentSchema> findAllAppointmentsByPacientId(@Param("doctorId") UUID pacientId);
}
