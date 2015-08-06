package me.kordun.office.front.security;

import me.kordun.enums.Role;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
/**
 * Wrapper of backend enum Role with frontend interface GrantedAuthority.
 * Just not to mix backend class with frontend interface and keep it separated.
 * */
public enum FrontRole implements GrantedAuthority, Serializable {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_COMPANYUSER;

    public static FrontRole fromRoleEnum(Role role) {
        return FrontRole.valueOf(role.name());
    }
    @Override
    public String getAuthority() {
        return this.toString();
    }
}
