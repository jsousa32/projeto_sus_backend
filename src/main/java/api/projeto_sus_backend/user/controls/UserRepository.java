package api.projeto_sus_backend.user.controls;

import api.projeto_sus_backend.application.controls.ApplicationProjections;
import api.projeto_sus_backend.user.entities.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * The Interface UserRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */

@Transactional
@Repository
public interface UserRepository<T extends UserSchema> extends JpaRepository<T, UUID> {

    Optional<T> findByEmail(@Param("email") String email);

    Optional<T> findByDocument(@Param("document") String document);

    @Query("""
            SELECT
                u
            FROM UserSchema u
            WHERE u.email = :email
            """)
    Optional<ApplicationProjections.UserDetails> loadUserByUsername(@Param("email") String email);
}
