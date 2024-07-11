package api.projeto_sus_backend.doctor.boundary;

import api.projeto_sus_backend.doctor.controls.DoctorProjections;
import api.projeto_sus_backend.doctor.entities.Doctor;
import api.projeto_sus_backend.utils.CustomPageable;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The Class DoctorController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@RestController
@RequestMapping(path = "v1/doctors")
@Tag(name = "Módulo de Médicos")
public class DoctorController {

    private final DoctorBusiness doctorBusiness;

    public DoctorController(DoctorBusiness doctorBusiness) {
        this.doctorBusiness = doctorBusiness;
    }

    /**
     * Endpoint responsável por criar um médico
     *
     * @param doctor;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por criar um novo médico")
    @PostMapping
    @JsonView(DoctorProjections.Create.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> save(@Validated(DoctorProjections.Create.class) @RequestBody Doctor doctor) {

        doctorBusiness.save(doctor);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável por buscar todos os médicos paginados
     *
     * @return ResponseEntity<Page < Doctor>>;
     */
    @Operation(summary = "Endpoint responsável por buscar todos os médicos paginados")
    @GetMapping("all")
    @JsonView(DoctorProjections.Page.class)
    public ResponseEntity<Page<Doctor>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sorting", required = false) String sorting,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Doctor> response = doctorBusiness.findAll(filter, CustomPageable.getInstance(page, size, sorting));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    /**
     * Endpoint responsável por buscar todos os médicos não paginados
     *
     * @return ResponseEntity<Page < Doctor>>;
     */
    @Operation(summary = "Endpoint responsável por buscar todos os médicos não paginados")
    @GetMapping("unpaged")
    @JsonView(DoctorProjections.Page.class)
    public ResponseEntity<Page<Doctor>> findAll(
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Doctor> response = doctorBusiness.findAll(filter, Pageable.unpaged());

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    /**
     * Endpoint responsável por buscar um médico
     *
     * @param id;
     * @return ResponseEntity<Doctor>;
     */
    @Operation(summary = "Endpoint responsável por buscar um médico")
    @GetMapping
    @JsonView(DoctorProjections.Resume.class)
    public ResponseEntity<Doctor> findById(@RequestParam(value = "id") UUID id) {

        Doctor response = doctorBusiness.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint responsável por editar um médico
     *
     * @param id;
     * @param doctor;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por editar um médico")
    @PatchMapping
    @JsonView(DoctorProjections.EditablesFields.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(
            @RequestParam(name = "id") UUID id,
            @Validated(DoctorProjections.EditablesFields.class) @RequestBody Doctor doctor
    ) {
        doctorBusiness.update(id, doctor);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável por reativar um médico
     *
     * @param id;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por reativar um médico")
    @PatchMapping("active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> active(@RequestParam(name = "id") UUID id) {

        doctorBusiness.active(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável por desativar um paciente
     *
     * @param id;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por desativar um paciente")
    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> disable(@RequestParam(name = "id") UUID id) {

        doctorBusiness.disable(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
