package api.projeto_sus_backend.utils.helpers;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

/**
 * The Class JwtHelper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 07/07/2024
 */
public class JwtHelper {

    public static boolean isAdministrator(JwtAuthenticationToken token) {
        return token.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public static boolean isDoctor(JwtAuthenticationToken token) {
        return token.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"));
    }

    public static String getUserEmail(JwtAuthenticationToken token) {
        return token.getName();
    }

    public static UUID getId(JwtAuthenticationToken token) {
        return UUID.fromString((String) token.getTokenAttributes().get("userId"));
    }
}
