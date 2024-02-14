package api.sus.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * The Class PrincipalDetails
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = 6883776946830099313L;

    private UUID id;

    private boolean disabled;

    private String email;

    @JsonIgnore
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !disabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !disabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !disabled;
    }

    @Override
    public boolean isEnabled() {
        return !disabled;
    }
}
