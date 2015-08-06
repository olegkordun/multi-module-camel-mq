package me.kordun.office.front.controller;

import me.kordun.enums.CardSystemName;
import me.kordun.enums.Currency;
import me.kordun.enums.Role;
import me.kordun.office.front.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ApiController {
    @Autowired
    private ApiService apiService;

    @RequestMapping(value =  "/all-roles-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getAllRoles() {
        return Arrays.asList(Role.values());
    }

    @RequestMapping(value =  "/currencies", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getCurrencies() {
        return Arrays.asList(Currency.values()).stream()
                .map(Currency::name)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = {"/api/roles", "/current-user-roles"}, method = RequestMethod.GET)
    @ResponseBody
    public Collection<? extends GrantedAuthority> getRoles() {
        return apiService.getRoles();
    }
}
