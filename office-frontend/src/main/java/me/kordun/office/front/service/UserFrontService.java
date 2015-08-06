package me.kordun.office.front.service;

import me.kordun.json.UserJson;
import me.kordun.messaging.dto.UserDTO;
import me.kordun.messaging.dto.UserPasswordDTO;
import me.kordun.messaging.routing.Routes;
import me.kordun.messaging.service.UserServiceInterface;
import org.apache.camel.Produce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class UserFrontService {
    private static final Logger log = LoggerFactory.getLogger("FrontUserDetailsService");

    @Produce(uri = Routes.USER_SERVICE)
    private UserServiceInterface userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserJson createUser(UserJson userJson) {
        UserDTO dto = userService.createUser(UserDTO.fromJson(userJson));
        return dto.toJson();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserJson updateUser(UserJson userJson) {
        UserDTO dto = userService.updateUser(UserDTO.fromJson(userJson));
        return dto.toJson();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserJson getUser(Long id) {
        UserDTO dto = userService.getUser(id);
        if(dto == null)
            throw new NoSuchElementException("User not found");
        return dto.toJson();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserJson> getAllUsers() {
        List<UserDTO> list = userService.getUserList();
        return list.stream()
                .map(UserDTO::toJson)
                .collect(toList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updatePassword(Long id, String passwordDigest) {
        UserPasswordDTO dto = UserPasswordDTO.builder()
                .id(id)
                .passwordDigest(passwordDigest)
                .build();
        userService.updatePasswordUser(dto);
    }

    public UserDTO getCurrentUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO dto = userService.getUserByUsername(userName);
        log.info("getCurrentUser. Found user {}", dto.getUserName());
        return dto;
    }
}
