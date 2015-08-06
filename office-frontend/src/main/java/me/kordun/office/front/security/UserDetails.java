package me.kordun.office.front.security;

import me.kordun.messaging.dto.UserDetailsDTO;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;


public class UserDetails implements Serializable, org.springframework.security.core.userdetails.UserDetails {
    private final Set<FrontRole> roles;
    private final String password;
    private final String username;

    public UserDetails(Set<FrontRole> roles, String password, String username) {
        this.roles = roles;
        this.password = password;
        this.username = username;
    }

    @Override
    public Set<FrontRole> getAuthorities() {
        return roles;
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

    public static UserDetails fromDTO(UserDetailsDTO dto)
    {
        return new UserDetails(dto.getRoles()
                .stream()
                .map(FrontRole::fromRoleEnum)
                .collect(Collectors.toSet()),
                dto.getPassword(), dto.getUsername());
    }
}
