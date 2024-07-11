package api.projeto_sus_backend.pacient.boundary;

import api.projeto_sus_backend.pacient.controls.PacientProjections;
import api.projeto_sus_backend.pacient.entities.Pacient;
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
 * The Class PacientController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@RestController
@RequestMapping(path = "v1/pacients")
@Tag(name = "Módulo de Pacientes")
public class PacientController {

    private final PacientBusiness pacientBusiness;

    public PacientController(PacientBusiness pacientBusiness) {
        this.pacientBusiness = pacientBusiness;
    }


    /**
     * Endpoint responsável por criar um paciente
     *
     * @param pacient;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por criar um novo paciente")
    @PostMapping
    @JsonView(PacientProjections.Create.class)
    public ResponseEntity<Void> save(@Validated(PacientProjections.Create.class) @RequestBody Pacient pacient) {

        pacientBusiness.save(pacient);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint responsável por buscar todos os pacientes paginados
     *
     * @param page;
     * @param size;
     * @param sorting;
     * @param filter;
     * @return ResponseEntity<Page < Pacient>>;
     */
    @Operation(summary = "Endpoint responsável por buscar todos os pacientes paginados")
    @GetMapping("all")
    @JsonView(PacientProjections.Page.class)
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DOCTOR')")
    public ResponseEntity<Page<Pacient>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sorting", required = false) String sorting,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Pacient> response = pacientBusiness.findAll(filter, CustomPageable.getInstance(page, size, sorting));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint responsável por buscar todos os pacientes não paginados
     *
     * @param filter;
     * @return ResponseEntity<Page < Pacient>>;
     */
    @Operation(summary = "Endpoint responsável por buscar todos os pacientes não paginados")
    @GetMapping("unpaged")
    @JsonView(PacientProjections.Page.class)
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_DOCTOR')")
    public ResponseEntity<Page<Pacient>> findAllUnpaged(
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Pacient> response = pacientBusiness.findAll(filter, Pageable.unpaged());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint responsável por buscar um paciente
     *
     * @param id;
     * @return ResponseEntity<Pacient>;
     */
    @Operation(summary = "Endpoint por responsável buscar um paciente")
    @GetMapping
    @JsonView(PacientProjections.Resume.class)
    public ResponseEntity<Pacient> findById(@RequestParam(value = "id") UUID id) {

        Pacient pacient = pacientBusiness.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(pacient);
    }

    /**
     * Endpoint responsável por editar um paciente
     *
     * @param id;
     * @param pacient;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por editar um paciente")
    @PatchMapping
    @JsonView(PacientProjections.EditablesFields.class)
    public ResponseEntity<Void> update(
            @RequestParam(value = "id") UUID id,
            @Validated(PacientProjections.EditablesFields.class) @RequestBody Pacient pacient) {
        pacientBusiness.update(id, pacient);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável por reativar um paciente
     *
     * @param id;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por reativar um paciente")
    @PatchMapping("active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> active(@RequestParam(value = "id") UUID id) {

        pacientBusiness.active(id);

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
    public ResponseEntity<Void> disable(@RequestParam(value = "id") UUID id) {

        pacientBusiness.disable(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
