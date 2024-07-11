package api.projeto_sus_backend.user.controls;

import api.projeto_sus_backend.user.entities.User;
import api.projeto_sus_backend.user.entities.UserSchema;

/**
 * The Class UserMapper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class UserMapper {

    public static User convert(UserSchema userSchema) {
        return new User.Builder().builder()
                .setId(userSchema.getId())
                .setType(userSchema.getType())
                .setFirstName(userSchema.getFirstName())
                .setLastName(userSchema.getLastName())
                .setEmail(userSchema.getEmail())
                .setDocument(userSchema.getDocument())
                .setTelephone(userSchema.getTelephone())
                .setPassword(userSchema.getPassword())
                .setPermissions(userSchema.getPermissions())
                .setCodeEmailConfirmation(userSchema.getCodeEmailConfirmation())
                .setEmailConfirmed(userSchema.isEmailConfirmed())
                .build();
    }
}
