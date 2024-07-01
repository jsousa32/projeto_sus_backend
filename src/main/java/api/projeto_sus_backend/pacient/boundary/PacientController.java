package api.projeto_sus_backend.pacient.boundary;

import api.projeto_sus_backend.pacient.entities.Pacient;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class PacientController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@RestController
@RequestMapping(path = "pacient")
public class PacientController {

    private final PacientBussiness pacientBussiness;

    public PacientController(PacientBussiness pacientBussiness) {
        this.pacientBussiness = pacientBussiness;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Pacient pacient) {

        pacientBussiness.save(pacient);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
