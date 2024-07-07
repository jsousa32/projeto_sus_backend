package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.entities.AccessTokenResponse;
import api.projeto_sus_backend.user.controls.UserGateway;
import api.projeto_sus_backend.user.entities.User;
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

    private final UserGateway userGateway;

    public JwtService(JwtEncoder jwtEncoder,
                      UserGateway userGateway) {
        this.jwtEncoder = jwtEncoder;
        this.userGateway = userGateway;
    }

    public AccessTokenResponse generateToken(Authentication authentication) {
        Instant createdAt = Instant.now();
        Instant expiresAt = LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));

        String authorities = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer(ISSUER).issuedAt(createdAt).expiresAt(expiresAt).subject(authentication.getName())
                .claim("authorities", authorities).build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        User user = userGateway.findByEmail(authentication.getName());

        return new AccessTokenResponse.Builder().builder().accessToken(token).name(user.getFirstName(), user.getLastName())
                .emailConfirmed(user.isEmailConfirmed()).createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusHours(3))
                .build();
    }
}
