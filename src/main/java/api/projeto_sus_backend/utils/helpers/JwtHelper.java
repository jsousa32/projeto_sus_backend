package api.projeto_sus_backend.utils.helpers;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

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

    public static String getUserEmail(JwtAuthenticationToken token) {
        return token.getName();
    }
}
