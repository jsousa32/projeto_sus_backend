package api.sus.repository;

import api.sus.model.schema.UserSchema;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The Interface UserRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserSchema, UUID> {

    @Query("""
            SELECT NEW api.sus.model.dto.PrincipalDetails(
            u.id, u.disabled, u.email, u.password
            )
            FROM UserSchema u
            WHERE LOWER(u.email) = LOWER(:email)
            """)
    UserDetails loadUserByUsername(@Param("email") String email);

    boolean existsByDocument(@Param("document") String document);

    boolean existsByEmail(@Param("email") String email);
}
