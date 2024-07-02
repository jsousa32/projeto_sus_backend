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

    public static Pacient convert(PacientProjections.Page pacientPage) {
        return new Pacient.Builder().builder()
                .setId(pacientPage.getId())
                .setFirstName(pacientPage.getFirstName())
                .setLastName(pacientPage.getLastName())
                .setEmail(pacientPage.getEmail())
                .setTelephone(pacientPage.getTelephone())
                .setSusNumber(pacientPage.getSusNumber())
                .build();
    }

    public static Pacient convert(PacientProjections.Resume pacientResume) {
        return new Pacient.Builder().builder()
                .setId(pacientResume.getId())
                .setFirstName(pacientResume.getFirstName())
                .setLastName(pacientResume.getLastName())
                .setEmail(pacientResume.getEmail())
                .setTelephone(pacientResume.getTelephone())
                .setDocument(pacientResume.getDocument())
                .setSusNumber(pacientResume.getSusNumber())
                .build();
    }
}
