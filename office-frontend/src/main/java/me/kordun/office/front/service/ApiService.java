package me.kordun.office.front.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ApiService {
    public Collection<? extends GrantedAuthority> getRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
