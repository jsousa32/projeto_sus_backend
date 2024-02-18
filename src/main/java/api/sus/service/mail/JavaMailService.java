package api.sus.service.mail;

import api.sus.model.dto.Pacient;
import api.sus.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class JavaMailService
 *
 * @author João Lucas Silva de Sousa
 * @sincer 14/02/2024
 */
@Service
public class JavaMailService {

    private final static String URL = "http://localhost:4200/";

    private final JavaMailBuilder javaMailBuilder;

    @Autowired
    public JavaMailService(JavaMailBuilder javaMailBuilder) {
        this.javaMailBuilder = javaMailBuilder;
    }


    public void senderEmailToken(Pacient pacient) {
        Map<String, Object> params = new HashMap<>();

        params.put("name", pacient.getFirstName() + " " + pacient.getLastName());
        params.put("token", pacient.getTokenEmail());

        javaMailBuilder.to(pacient.getEmail())
                .subject("Confirmação de E-mail").fire("autenticacao/confirmar_email/body.ftl", params);
    }

    public void senderRecoveryPassword(User user) {
        String decodedEmail = Base64.getEncoder().encodeToString(user.getEmail().getBytes());

        String finalUrl = URL + "reset-password/" + decodedEmail;

        Map<String, Object> params = new HashMap<>();

        params.put("name", user.getFirstName() + " " + user.getLastName());
        params.put("url", finalUrl);

        javaMailBuilder.to(user.getEmail())
                .subject("Recuperação de Senha").fire("autenticacao/recuperar_senha/body.ftl", params);
    }
}
