package api.sus.controlller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class StatsController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@RestController
@RequestMapping(path = "stats")
public class StatsController {

    @GetMapping
    public ResponseEntity<String> stats() {
        return ResponseEntity.status(HttpStatus.OK).body("Servidor online!");
    }
}
