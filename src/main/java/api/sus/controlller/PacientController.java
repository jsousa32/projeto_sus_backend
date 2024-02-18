package api.sus.controlller;

import api.sus.business.PacientBusiness;
import api.sus.model.dto.Pacient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class UserController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 14/02/2024
 */
@RestController
@RequestMapping(path = "pacient")
public class PacientController {

    private final PacientBusiness pacientBusiness;

    @Autowired
    public PacientController(PacientBusiness pacientBusiness) {
        this.pacientBusiness = pacientBusiness;
    }


    @PostMapping("signup")
    public ResponseEntity<Pacient> signup(@RequestBody @Valid Pacient pacient) {
        Pacient response = pacientBusiness.save(pacient);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
