package api.projeto_sus_backend.application.boundary;

import api.projeto_sus_backend.application.controls.ApplicationException;
import api.projeto_sus_backend.application.entities.AccessTokenResponse;
import api.projeto_sus_backend.application.entities.PrincipalDetails;
import api.projeto_sus_backend.user.controls.UserGateway;
import api.projeto_sus_backend.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
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
 * @sincer 04/07/2024
 */
@Component
public class AuthBusiness {

    private final static String AUTHORIZATION = "Authorization";

    private final static String BASIC = "Basic ";

    private final PrincipalDetailsGateway principalDetailsGateway;

    private final JwtService jwtService;

    private final UserGateway userGateway;

    private final AuthenticationManager authenticationManager;

    public AuthBusiness(PrincipalDetailsGateway principalDetailsGateway,
                        JwtService jwtService,
                        UserGateway userGateway,
                        AuthenticationManager authenticationManager) {
        this.principalDetailsGateway = principalDetailsGateway;
        this.jwtService = jwtService;
        this.userGateway = userGateway;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Metodo responsável por realizar o login na aplicação
     *
     * @return AccessTokenResponse;
     */
    public AccessTokenResponse login() {
        String[] credentials = getCredentials();

        UserDetails userDetails = principalDetailsGateway.loadUserByUsername(credentials[0]);

        if (Objects.isNull(userDetails)) {
            throw new ApplicationException("Credenciais incorretas", HttpStatus.UNAUTHORIZED);
        }

        if (!new BCryptPasswordEncoder().matches(credentials[1], userDetails.getPassword())) {
            throw new ApplicationException("Credenciais incorretas", HttpStatus.UNAUTHORIZED);
        }

        User user = userGateway.findByEmail(credentials[0]);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);

        Authentication authenticate = authenticationManager.authenticate(authentication);

        return jwtService.getAccessToken((PrincipalDetails) authenticate.getPrincipal());
    }

    /**
     * Metodo responsável por retornar as credenciais do header da requisição
     *
     * @return String[];
     */
    private static String[] getCredentials() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (Objects.isNull(servletRequestAttributes)) {
            throw new ApplicationException("Não foi possível concluir a requisição", HttpStatus.BAD_REQUEST);
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();

        if (!existsHeaderAuthorization(request)) {
            throw new ApplicationException("Sem autorização", HttpStatus.UNAUTHORIZED);
        }

        return base64Decoded(request.getHeader(AUTHORIZATION).replace(BASIC, "")).split(":");
    }

    /**
     * Metodo responsável por validar se existe a Authorização no header da requisição
     *
     * @param request;
     * @return boolean;
     */
    private static boolean existsHeaderAuthorization(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        return Objects.nonNull(header) && header.startsWith(BASIC);
    }

    /**
     * Metodo responsável por decodificar o base64 do Basic Authentication
     *
     * @param request;
     * @return String;
     */
    private static String base64Decoded(String request) {
        byte[] decode = Base64.getDecoder().decode(request);

        return new String(decode);
    }
}
