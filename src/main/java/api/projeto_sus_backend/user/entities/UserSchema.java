package api.projeto_sus_backend.user.entities;

import api.projeto_sus_backend.generic.entities.GenericSchema;
import api.projeto_sus_backend.user.entities.enums.Permissions;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Class UserSchema
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
@Entity
@Table(name = "tb_users", indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "document"),
})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", length = 10, discriminatorType = DiscriminatorType.STRING)
public class UserSchema extends GenericSchema {

    public final static String DESCIMINATOR_PACIENT = "Pacient";

    public final static String DESCIMINATOR_DOCTOR = "Doctor";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 11)
    private String telephone;

    @Column(nullable = false, length = 11, unique = true)
    private String document;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "tb_user_permissions", joinColumns = @JoinColumn(name = "user_id"),
            indexes = {@Index(columnList = "user_id")})
    @Column(name = "permission")
    private List<Permissions> permissions = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    protected void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }
}
