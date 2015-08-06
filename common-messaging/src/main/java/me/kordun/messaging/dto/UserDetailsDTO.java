package me.kordun.messaging.dto;

import me.kordun.enums.Role;

import java.io.Serializable;
import java.util.Set;

public final class UserDetailsDTO implements Serializable {
    private final Set<Role> roles;
    private final String password;
    private final String username;


    public UserDetailsDTO(Set<Role> roles, String password, String username) {
        this.roles = roles;
        this.password = password;
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
