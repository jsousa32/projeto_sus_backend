package api.sus.business;

import api.sus.exception.GlobalException;
import api.sus.gateway.PacientGateway;
import api.sus.gateway.UserGateway;
import api.sus.model.dto.Pacient;
import api.sus.model.dto.PrincipalDetails;
import api.sus.model.dto.User;
import api.sus.model.record.AccessToken;
import api.sus.service.CustomDetailsService;
import api.sus.service.JwtService;
import api.sus.service.mail.JavaMailService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
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

    private final JavaMailService javaMailService;

    private final PacientGateway pacientGateway;

    private final UserGateway userGateway;

    private final JwtService jwtService;

    @Autowired
    public AuthBusiness(CustomDetailsService customDetailsService,
                        AuthenticationManager authenticationManager,
                        JavaMailService javaMailService,
                        PacientGateway pacientGateway,
                        UserGateway userGateway,
                        JwtService jwtService) {
        this.customDetailsService = customDetailsService;
        this.authenticationManager = authenticationManager;
        this.javaMailService = javaMailService;
        this.pacientGateway = pacientGateway;
        this.userGateway = userGateway;
        this.jwtService = jwtService;
    }


    /**
     * Método responsável por realizar o login na plataforma via Basic Auth;
     *
     * @return AccessToken;
     */
    public AccessToken login() {
        String[] credentials = getCredentials();

        UserDetails userDetails = customDetailsService.loadUserByUsername(credentials[0]);

        if (Objects.isNull(userDetails)) {
            throw new GlobalException("Credenciais incorretas", HttpStatus.UNAUTHORIZED);
        }

        if (!new BCryptPasswordEncoder().matches(credentials[1], userDetails.getPassword())) {
            throw new GlobalException("Credenciais incorretas", HttpStatus.UNAUTHORIZED);
        }

        User user = userGateway.findByEmail(credentials[0]);

        if (!user.isConfirmedEmail()) {
            return null;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return jwtService.generateToken((PrincipalDetails) authenticate.getPrincipal());
    }

    /**
     * Método responsável por realizar a confirmação do e-mail do paciente;
     *
     * @param token;
     * @return AccessToken;
     */
    public void confirmPacientEmail(String token) {
        String[] credentials = getCredentials();

        User user = userGateway.findByEmail(credentials[0]);

        if (ObjectUtils.notEqual(user.getTokenEmail(), token)) {
            throw new GlobalException("O token está inválido", HttpStatus.BAD_REQUEST);
        }

        user.setConfirmedEmail(true);

        userGateway.save(user);
    }

    /**
     * Metodo responsável por enviar e-mail de recuperação da senha do usuário;
     *
     * @param email;
     */
    public void forgetPassword(String email) {
        User user = userGateway.findByEmail(email);

        if (Objects.isNull(user)) {
            throw new GlobalException("E-mail de usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        javaMailService.senderRecoveryPassword(user);
    }

    /**
     * Método responsável por resetar a senha do usuário;
     */
    public void resetPassword() {
        String[] credentials = getCredentials();

        User user = userGateway.findByEmail(credentials[0]);

        user.setPassword(credentials[1]);

        user.encryptPassword();

        if(user instanceof Pacient) {
            pacientGateway.save((Pacient) user);
        }
    }

    /**
     * Método responsável por pegar as credenciais do cabeçalho da requisição;
     *
     * @return String[];
     */
    private String[] getCredentials() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (Objects.isNull(servletRequestAttributes)) {
            throw new GlobalException("Não foi possível concluir a requisição", HttpStatus.BAD_REQUEST);
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();

        if (!existsHeaderAuthorization(request)) {
            throw new GlobalException("Sem autorização", HttpStatus.UNAUTHORIZED);
        }

        return base64Decoded(request.getHeader(AUTHORIZATION).replace(BASIC, "")).split(":");
    }

    /**
     * Método responsável por decodificar as credenciais do cabeçalho da requisição;
     *
     * @param request;
     * @return String;
     */
    private String base64Decoded(String request) {
        byte[] decode = Base64.getDecoder().decode(request);

        return new String(decode);
    }

    /**
     * Método responsável por verificar se o cabeçalho da requisição existe e nas condições do Basic Auth;
     *
     * @param request;
     * @return boolean;
     */
    private boolean existsHeaderAuthorization(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        return Objects.nonNull(header) && header.startsWith(BASIC);
    }
}
