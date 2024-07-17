package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.entities.UserSession;
import api.projeto_sus_backend.application.entities.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

/**
 * The Class JwtService
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
@Service
public class JwtService {

    private final static String ISSUER = "SUS-Api";

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Metodo responsável por gerar o token
     *
     * @param authentication;
     * @return AccessTokenResponse;
     */
    public UserSession generateToken(Authentication authentication) {
        Instant createdAt = Instant.now();
        Instant expiresAt = LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));

        String permissions = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer(ISSUER).issuedAt(createdAt).expiresAt(expiresAt).subject(authentication.getName())
                .claim("permissions", permissions)
                .claim("userId", ((PrincipalDetails) authentication.getPrincipal()).getId())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return generateResponse(token, (PrincipalDetails) authentication.getPrincipal());
    }

    /**
     * Metodo responsável por gerar o objeto de respsota ao login
     *
     * @param token;
     * @param principal;
     * @return AccessTokenResponse;
     */
    private UserSession generateResponse(String token, PrincipalDetails principal) {
        LocalDateTime createdAt = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime expiresAt = createdAt.plusHours(3);

        return new UserSession.Builder().builder().accessToken(token).name(principal.getName())
                .setPermissions(principal.getPermissions())
                .setUserId(principal.getId())
                .emailConfirmed(principal.isEmailConfirmed()).createdAt(createdAt).expiresAt(expiresAt).build();
    }
}
