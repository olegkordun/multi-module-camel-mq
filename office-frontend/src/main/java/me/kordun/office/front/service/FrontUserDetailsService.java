package me.kordun.office.front.service;

import me.kordun.messaging.dto.UserDetailsDTO;
import me.kordun.messaging.routing.Routes;
import me.kordun.messaging.service.UserServiceInterface;
import me.kordun.office.front.security.UserDetails;
import org.apache.camel.Produce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FrontUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger("FrontUserDetailsService");

    @Produce(uri = Routes.USER_SERVICE)
    private UserServiceInterface userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("Loading userDetails via CAMEL->MQ->Backend by name '{}' ", username);
        UserDetailsDTO userDTO = userService.getUserDetailsByUsername(username);
        if(userDTO == null) throw new UsernameNotFoundException("not found");

        return UserDetails.fromDTO(userDTO);
    }
}
