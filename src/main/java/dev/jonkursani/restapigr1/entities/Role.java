package dev.jonkursani.restapigr1.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static dev.jonkursani.restapigr1.entities.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(ADMIN_READ, ADMIN_WRITE, MANAGER_WRITE, MANAGER_READ)
    ),
    MANAGER(
            Set.of(MANAGER_READ, MANAGER_WRITE)
    ),
    USER(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<>(getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());

        // Spring security konventa e permissions -> ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}