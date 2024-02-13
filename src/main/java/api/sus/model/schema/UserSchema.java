package api.sus.model.schema;

import api.sus.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Class UserSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserSchema extends GenericSchema {

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(length = 30)
    private String email;

    @Column(length = 15)
    private String telephone;

    @Column(length = 20)
    private String document;

    @Column(length = 100)
    private String password;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
