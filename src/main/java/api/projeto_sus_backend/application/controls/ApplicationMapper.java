package api.projeto_sus_backend.application.controls;

import api.projeto_sus_backend.application.entities.ForgotPassword;
import api.projeto_sus_backend.application.entities.ForgotPasswordSchema;
import api.projeto_sus_backend.application.entities.PrincipalDetails;

/**
 * The Class ApplicationMapper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class ApplicationMapper {

    public static PrincipalDetails convert(ApplicationProjections.UserDetails userDetails) {
        return new PrincipalDetails.Builder().builder()
                .setId(userDetails.getId())
                .setName(userDetails.getFirstName().concat(" ").concat(userDetails.getLastName()))
                .setEmailConfirmed(userDetails.isEmailConfirmed())
                .setDisabled(userDetails.isDisabled())
                .setEmail(userDetails.getEmail())
                .setPassword(userDetails.getPassword())
                .setPermissions(userDetails.getPermissions())
                .build();
    }

    public static ForgotPasswordSchema convert(ForgotPassword forgotPassword) {
        return new ForgotPasswordSchema.Builder().builder()
                .setId(forgotPassword.getId())
                .setUserId(forgotPassword.getUserId())
                .setExpiresAt(forgotPassword.getExpiresAt())
                .build();
    }

    public static ForgotPassword convert(ForgotPasswordSchema forgotPassword) {
        return new ForgotPassword.Builder().builder()
                .setId(forgotPassword.getId())
                .setUserId(forgotPassword.getUserId())
                .setExpiresAt(forgotPassword.getExpiresAt())
                .build();
    }
}
