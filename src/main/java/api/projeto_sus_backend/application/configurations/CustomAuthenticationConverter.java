package api.projeto_sus_backend.application.configurations;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The Class CustomAuthenticationConverter
 *
 * @author João Lucas Silva de Sousa
 * @sincer 06/07/2024
 */
public class CustomAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        String roles = source.getClaim("authorities");

        if(Objects.isNull(roles) || roles.isBlank()) {
            return new JwtAuthenticationToken(source);
        }

        String[] arr = roles.split(",\\s");

        List<SimpleGrantedAuthority> grants = Arrays.stream(arr).map(role -> new SimpleGrantedAuthority(role.toUpperCase())).toList();

        return new JwtAuthenticationToken(source, grants);
    }
}
