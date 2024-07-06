package api.projeto_sus_backend.application.entities;

import api.projeto_sus_backend.user.entities.enums.Permissions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * The Class PrincipalDetails
 *
 * @author João Lucas Silva de Sousa
 * @sincer 04/07/2024
 */
public class PrincipalDetails implements UserDetails {

    private UUID id;

    private String name;

    private boolean emailConfirmed;

    private boolean disabled;

    private String email;

    private String password;

    private final List<Permissions> permissions = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream().map(p -> new SimpleGrantedAuthority("ROLE_" + p.name().toUpperCase())).toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.disabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.disabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.disabled;
    }

    @Override
    public boolean isEnabled() {
        return !this.disabled;
    }

    /**
     * The Builder of PrincipalDetails
     */
    public static class Builder {
        private PrincipalDetails principalDetails;

        public Builder builder() {
            this.principalDetails = new PrincipalDetails();
            return this;
        }

        public Builder setId(UUID id) {
            this.principalDetails.setId(id);
            return this;
        }

        public Builder setName(String name) {
            this.principalDetails.setName(name);
            return this;
        }

        public Builder setEmailConfirmed(boolean emailConfirmed) {
            this.principalDetails.setEmailConfirmed(emailConfirmed);
            return this;
        }

        public Builder setDisabled(boolean disabled) {
            this.principalDetails.setDisabled(disabled);
            return this;
        }

        public Builder setEmail(String email) {
            this.principalDetails.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            this.principalDetails.setPassword(password);
            return this;
        }

        public Builder setPermissions(List<Permissions> permissions) {
            this.principalDetails.getPermissions().addAll(permissions);
            return this;
        }

        public PrincipalDetails build() {
            return this.principalDetails;
        }
    }
}
