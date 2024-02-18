package api.sus.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import api.sus.exception.GlobalException;
import api.sus.model.dto.PrincipalDetails;
import api.sus.model.record.AccessToken;

/**
 * The Class JwtService
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Service
public class JwtService {

    private final static String SECRET = "TOKEN";
    private final static String ISSUER = "SUS-Api";

    public AccessToken generateToken(PrincipalDetails principalDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            String token = JWT.create().withIssuer(ISSUER).withSubject(principalDetails.getEmail()).withExpiresAt(genExpirationDate()).sign(algorithm);

            return new AccessToken(token);

        } catch (JWTCreationException exception) {
            throw new GlobalException("Erro ao criar o token JWT", HttpStatus.BAD_REQUEST);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm).withIssuer(ISSUER).build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new GlobalException("Erro ao validator o token JWT", HttpStatus.BAD_REQUEST);
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
