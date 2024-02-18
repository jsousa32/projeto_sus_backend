package api.sus.model.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Class DoctorSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 18/02/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_doctors")
@DiscriminatorValue(UserSchema.DISCRIMINATOR_DOCTOR)
@PrimaryKeyJoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_doctor_x_user"))
public class DoctorSchema extends UserSchema {

    @Column(length = 10)
    private String crm;
}
