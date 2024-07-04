package api.projeto_sus_backend.application.boundary;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * The Class SecurityFilter
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final static String AUTHORIZATION = "Authorization";

    private final static String BEARER = "Bearer ";

    private final JwtService jwtService;

    private final PrincipalDetailsGateway principalDetailsGateway;

    public SecurityFilter(JwtService jwtService, PrincipalDetailsGateway principalDetailsGateway) {
        this.jwtService = jwtService;
        this.principalDetailsGateway = principalDetailsGateway;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (existsTokenOnHeader(request)) {
            String header = getAuthorizationOnHeader(request);

            header = header.replace(BEARER, "");

            String email = jwtService.validateToken(header);

            UserDetails userDetails = principalDetailsGateway.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private static boolean existsTokenOnHeader(HttpServletRequest request) {
        String header = getAuthorizationOnHeader(request);

        return Objects.nonNull(header) && header.startsWith(BEARER);
    }

    private static String getAuthorizationOnHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
