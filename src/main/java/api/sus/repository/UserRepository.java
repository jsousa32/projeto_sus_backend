package api.sus.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api.sus.model.schema.UserSchema;
import jakarta.transaction.Transactional;

/**
 * The Interface UserRepository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserSchema, UUID> {

    boolean existsByDocument(@Param("document") String document);

    boolean existsByEmail(@Param("email") String email);
}
