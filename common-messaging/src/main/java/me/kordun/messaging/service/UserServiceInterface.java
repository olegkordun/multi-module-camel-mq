package me.kordun.messaging.service;

import me.kordun.messaging.dto.UserDTO;
import me.kordun.messaging.dto.UserDetailsDTO;
import me.kordun.messaging.dto.UserPasswordDTO;

import java.util.List;

public interface UserServiceInterface {
    UserDTO createUser(UserDTO dto);
    UserDTO updateUser(UserDTO dto);
    UserDTO updatePasswordUser(UserPasswordDTO dto);
    UserDetailsDTO getUserDetailsByUsername(String username);
    List<UserDTO> getUserList();
    UserDTO getUser(Long id);
    UserDTO getUserByUsername(String username);
}
