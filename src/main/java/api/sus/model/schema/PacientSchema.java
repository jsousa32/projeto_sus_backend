package api.sus.model.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Class PacientSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 18/02/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pacients")
@DiscriminatorValue(UserSchema.DISCRIMINATOR_PACIENT)
@PrimaryKeyJoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_pacient_x_user"))
public class PacientSchema extends UserSchema {

    @Column(length = 20)
    private String sus;
}
