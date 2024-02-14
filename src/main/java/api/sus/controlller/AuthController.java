package api.sus.controlller;

import api.sus.business.AuthBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class AuthController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@RestController
@RequestMapping(path = "auth")
public class AuthController {

    private final AuthBusiness authBusiness;

    @Autowired
    public AuthController(AuthBusiness authBusiness) {
        this.authBusiness = authBusiness;
    }

    @PostMapping("login")
    public ResponseEntity<String> login() {
        String response = authBusiness.login();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

