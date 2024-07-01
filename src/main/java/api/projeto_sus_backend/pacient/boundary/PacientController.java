package api.projeto_sus_backend.pacient.boundary;

import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.utils.CustomPageable;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The Class PacientController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@RestController
@RequestMapping(path = "pacient")
public class PacientController {

    private final PacientBusiness pacientBusiness;

    public PacientController(PacientBusiness pacientBusiness) {
        this.pacientBusiness = pacientBusiness;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Pacient pacient) {

        pacientBusiness.save(pacient);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("all")
    public ResponseEntity<Page<Pacient>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sorting", required = false) String sorting,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Pacient> response = pacientBusiness.findAll(filter, CustomPageable.getInstance(page, size, sorting));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("unpaged")
    public ResponseEntity<Page<Pacient>> findAllUnpaged(
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Pacient> response = pacientBusiness.findAll(filter, Pageable.unpaged());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Pacient> findById(@PathVariable UUID id) {

        Pacient pacient = pacientBusiness.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(pacient);
    }

    @PatchMapping
    public ResponseEntity<Void> update(
            @RequestParam(value = "id") UUID id,
            @Valid @RequestBody Pacient pacient) {
        pacientBusiness.update(id, pacient);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("active")
    public ResponseEntity<Void> active(@RequestParam(value = "id") UUID id) {

        pacientBusiness.disable(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> disable(@RequestParam(value = "id") UUID id) {

        pacientBusiness.disable(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
