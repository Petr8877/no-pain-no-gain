package nopainnogain.auditservice.core.dto;

import nopainnogain.auditservice.core.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DetailsDto implements UserDetails {

    private final Role role;
    private final String password;
    private final String username;
    private final String fio;
    private final UUID uuid;

    public DetailsDto(String role, String password, String username, String fio, String uuid) {
        this.role = Role.valueOf(role);
        this.password = password;
        this.username = username;
        this.fio = fio;
        this.uuid = UUID.fromString(uuid);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role role() {
        return role;
    }

    public String getFio() {
        return fio;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Role getRole() {
        return role;
    }
}
