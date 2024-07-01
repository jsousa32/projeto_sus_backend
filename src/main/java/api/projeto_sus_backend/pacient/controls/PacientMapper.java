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
                .setId(pacient.getId())
                .setFirstName(pacient.getFirstName())
                .setLastName(pacient.getLastName())
                .setEmail(pacient.getEmail())
                .setTelephone(pacient.getTelephone())
                .setDocument(pacient.getDocument())
                .setSusNumber(pacient.getSusNumber())
                .setPassword(pacient.getPassword())
                .setDisabledAt(pacient.getDisabledAt())
                .setDisabled(pacient.isDisabled())
                .build();
    }

    public static Pacient convert(PacientSchema pacientSchema) {
        return new Pacient.Builder().builder()
                .setId(pacientSchema.getId())
                .setFirstName(pacientSchema.getFirstName())
                .setLastName(pacientSchema.getLastName())
                .setEmail(pacientSchema.getEmail())
                .setTelephone(pacientSchema.getTelephone())
                .setPassword(pacientSchema.getPassword())
                .setDocument(pacientSchema.getDocument())
                .setSusNumber(pacientSchema.getSusNumber())
                .setCreatedAt(pacientSchema.getCreatedAt())
                .setUpdatedAt(pacientSchema.getUpdatedAt())
                .setDisabledAt(pacientSchema.getDisabledAt())
                .setDisabled(pacientSchema.isDisabled())
                .build();
    }

    public static Pacient convert(PacientProjections.PacientPageProjection pacientPageProjection) {
        return new Pacient.Builder().builder()
                .setId(pacientPageProjection.getId())
                .setFirstName(pacientPageProjection.getFirstName())
                .setLastName(pacientPageProjection.getLastName())
                .setEmail(pacientPageProjection.getEmail())
                .setTelephone(pacientPageProjection.getTelephone())
                .build();
    }

    public static Pacient convert(PacientProjections.PacientResumeProjection pacientResumeProjection) {
        return new Pacient.Builder().builder()
                .setId(pacientResumeProjection.getId())
                .setFirstName(pacientResumeProjection.getFirstName())
                .setLastName(pacientResumeProjection.getLastName())
                .setEmail(pacientResumeProjection.getEmail())
                .setTelephone(pacientResumeProjection.getTelephone())
                .setDocument(pacientResumeProjection.getDocument())
                .build();
    }
}
