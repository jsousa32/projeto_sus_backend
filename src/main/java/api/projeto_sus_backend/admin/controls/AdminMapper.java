package api.projeto_sus_backend.admin.controls;

import api.projeto_sus_backend.admin.entities.Admin;
import api.projeto_sus_backend.admin.entities.AdminSchema;

/**
 * The Class AdminMapper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
public class AdminMapper {

    public static AdminSchema convert(Admin admin) {
        return new AdminSchema.Builder().builder()
                .setId(admin.getId())
                .setFirstName(admin.getFirstName())
                .setLastName(admin.getLastName())
                .setEmail(admin.getEmail())
                .setDocument(admin.getDocument())
                .setPassword(admin.getPassword())
                .setPermissions(admin.getPermissions())
                .setCodeEmailConfirmation(admin.getCodeEmailConfirmation())
                .setEmailConfirmed(admin.isEmailConfirmed())
                .build();
    }

    public static Admin convert(AdminSchema adminSchema) {
        return new Admin.Builder().builder()
                .setId(adminSchema.getId())
                .setFirstName(adminSchema.getFirstName())
                .setLastName(adminSchema.getLastName())
                .setEmail(adminSchema.getEmail())
                .setDocument(adminSchema.getDocument())
                .setPassword(adminSchema.getPassword())
                .setPermissions(adminSchema.getPermissions())
                .setCodeEmailConfirmation(adminSchema.getCodeEmailConfirmation())
                .setEmailConfirmed(adminSchema.isEmailConfirmed())
                .build();
    }

    public static Admin convert(AdminProjections.Page adminPage) {
        return new Admin.Builder().builder()
                .setId(adminPage.getId())
                .setFirstName(adminPage.getFirstName())
                .setLastName(adminPage.getLastName())
                .setEmail(adminPage.getEmail())
                .build();
    }
}
