package api.sus.business;

import api.sus.exception.GlobalException;
import api.sus.model.dto.PrincipalDetails;
import api.sus.service.CustomDetailsService;
import api.sus.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Base64;
import java.util.Objects;

/**
 * The Class AuthBusiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Component
public class AuthBusiness {

    private final static String AUTHORIZATION = "Authorization";

    private final static String BASIC = "Basic ";

    private final CustomDetailsService customDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Autowired
    public AuthBusiness(CustomDetailsService customDetailsService,
                        AuthenticationManager authenticationManager,
                        JwtService jwtService) {
        this.customDetailsService = customDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public String login() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if(Objects.isNull(servletRequestAttributes)) {
            throw new GlobalException("Não foi possível concluir a requisição", HttpStatus.BAD_REQUEST);
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();

        if(!existsHeaderAuthorization(request)) {
            throw new GlobalException("Sem autorização", HttpStatus.UNAUTHORIZED);
        }

        String[] credentials = base64Decoded(getHeaderAuthorization(request).replace(BASIC, "")).split(":");

        UserDetails userDetails = customDetailsService.loadUserByUsername(credentials[0]);

        if (Objects.isNull(userDetails)) {
            throw new GlobalException("Credenciais incorretas", HttpStatus.UNAUTHORIZED);
        }

        if (!new BCryptPasswordEncoder().matches(credentials[1], userDetails.getPassword())) {
            throw new GlobalException("Credenciais incorretas", HttpStatus.UNAUTHORIZED);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return jwtService.generateToken((PrincipalDetails) authenticate.getPrincipal());
    }

    private String base64Decoded(String request) {
        byte[] decode = Base64.getDecoder().decode(request);

        return new String(decode);
    }

    private boolean existsHeaderAuthorization(HttpServletRequest request) {
        String header = getHeaderAuthorization(request);

        return Objects.nonNull(header) && header.startsWith(BASIC);
    }

    private String getHeaderAuthorization(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
