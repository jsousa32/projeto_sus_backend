package api.projeto_sus_backend.application.controls;

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
                .setDisabled(userDetails.isDisabled())
                .setEmail(userDetails.getEmail())
                .setPassword(userDetails.getPassword())
                .setPermissions(userDetails.getPermissions())
                .build();
    }
}
