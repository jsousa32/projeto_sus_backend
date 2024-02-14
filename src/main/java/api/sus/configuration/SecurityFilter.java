package api.sus.configuration;

import api.sus.service.CustomDetailsService;
import api.sus.service.JwtService;
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
 * @sincer 13/02/2024
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final static String AUTHORIZATION = "Authorization";

    private final static String BEARER = "Bearer ";

    private final JwtService jwtService;

    private final CustomDetailsService customDetailsService;

    public SecurityFilter(JwtService jwtService,
                          CustomDetailsService customDetailsService) {
        this.jwtService = jwtService;
        this.customDetailsService = customDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (existsTokenHeader(request)) {
            String header = getAuthorizationHeader(request);

            header = header.replace(BEARER, "");

            String email = jwtService.validateToken(header);

            UserDetails userDetails = customDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private boolean existsTokenHeader(HttpServletRequest request) {
        String header = getAuthorizationHeader(request);

        return Objects.nonNull(header) && header.startsWith(BEARER);
    }

    private String getAuthorizationHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
