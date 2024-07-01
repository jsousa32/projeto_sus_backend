package api.projeto_sus_backend.pacient.controls;

import api.projeto_sus_backend.pacient.entities.Pacient;
import api.projeto_sus_backend.pacient.entities.PacientSchema;

/**
 * The Class PacientMapper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class PacientMapper {

    public static PacientSchema convert(Pacient pacient) {
        return new PacientSchema.Builder().builder()
                .setFirstName(pacient.getFirstName())
                .setLastName(pacient.getLastName())
                .setEmail(pacient.getEmail())
                .setTelephone(pacient.getTelephone())
                .setDocument(pacient.getDocument())
                .setSusNumber(pacient.getSusNumber())
                .setCreatedAt(pacient.getCreatedAt())
                .setUpdatedAt(pacient.getUpdatedAt())
                .setDisabledAt(pacient.getDisabledAt())
                .setDisabled(pacient.isDisabled())
                .build();
    }

    public static Pacient convert(PacientSchema pacientSchema) {
        return new Pacient.Builder().builder()
                .setFirstName(pacientSchema.getFirstName())
                .setLastName(pacientSchema.getLastName())
                .setEmail(pacientSchema.getEmail())
                .setTelephone(pacientSchema.getTelephone())
                .setDocument(pacientSchema.getDocument())
                .setSusNumber(pacientSchema.getSusNumber())
                .setCreatedAt(pacientSchema.getCreatedAt())
                .setUpdatedAt(pacientSchema.getUpdatedAt())
                .setDisabledAt(pacientSchema.getDisabledAt())
                .setDisabled(pacientSchema.isDisabled())
                .build();
    }
}
