package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.controls.ApplicationException;
import api.projeto_sus_backend.application.entities.AccessTokenResponse;
import api.projeto_sus_backend.application.entities.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * The Class JwtService
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
@Service
public class JwtService {

    private final static String SECRET = "TOKEN";

    private final static String ISSUER = "SUS-Api";

    /**
     * Metodo responsável por gerar um token
     *
     * @param principalDetails;
     * @return AccessTokenResponse;
     */
    public AccessTokenResponse getAccessToken(PrincipalDetails principalDetails) {
        try {
            Algorithm algorithm = getAlgorithm();

            String token = JWT.create().withIssuer(ISSUER).withSubject(principalDetails.getEmail())
                    .withExpiresAt(genExpirationDate()).sign(algorithm);

            return new AccessTokenResponse(token);
        } catch (JWTCreationException exception) {
            throw new ApplicationException("Erro ao criar o JWT", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Metodo responsável por validar o token e retornar o subject do mesmo
     *
     * @param token;
     * @return String;
     */
    public String validateToken(String token) {
        try {

            Algorithm algorithm = getAlgorithm();

            return JWT.require(algorithm).withIssuer(ISSUER).build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new ApplicationException("Erro ao validator o token JWT", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Metodo responsável por criar o algoritmo do jwt
     *
     * @return Algorithm;
     */
    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET);
    }


    /**
     * Metodo responsável por gerar o instante de expiração do token
     *
     * @return Instant;
     */
    private static Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
