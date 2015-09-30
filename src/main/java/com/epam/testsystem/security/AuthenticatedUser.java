package com.epam.testsystem.security;

import com.epam.testsystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AuthenticatedUser extends User implements UserDetails {
    public AuthenticatedUser(User user) {
        if(user != null) {
            this.setId(user.getId());
            this.setCreatedAt(user.getCreatedAt());
            this.setUpdatedAt(user.getUpdatedAt());
            this.setCreatedBy(user.getCreatedBy());
            this.setUpdatedBy(user.getCreatedBy());
            this.setUsername(user.getUsername());
            this.setEmail(user.getEmail());
            this.setFirstName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setPassword(user.getPassword());
            this.setRole(user.getRole());
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authSet = new HashSet<>();
        authSet.add(new SimpleGrantedAuthority("ROLE_" + this.getRoleName().toUpperCase()));
        return authSet;
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


}
