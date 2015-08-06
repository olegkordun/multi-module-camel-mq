package me.kordun.office.backend.service;

import me.kordun.enums.Role;
import me.kordun.messaging.dto.UserDTO;
import me.kordun.messaging.dto.UserDetailsDTO;
import me.kordun.messaging.dto.UserPasswordDTO;
import me.kordun.messaging.service.UserServiceInterface;
import me.kordun.office.backend.camel.BeanOfficeBackendNames;
import me.kordun.persistent.entity.Company;
import me.kordun.persistent.entity.User;
import me.kordun.persistent.repository.CompanyRepository;
import me.kordun.persistent.repository.UserRepository;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service(value = BeanOfficeBackendNames.USER_SERVICE)
public class UserService implements UserServiceInterface {
    private static final Logger log = LoggerFactory.getLogger("UserService");

    public UserService() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDTO createUser(UserDTO dto) {
        log.info("Creating User {}", dto.toString());
        User u = getUser(dto, new User());
        u.setPassword(dto.getPasswordDigest());
        u = userRepository.save(u);
        return fromEntity(u);
    }

    @Override
    public UserDTO updateUser(UserDTO dto) {
        log.info("Updating User {}", dto.toString());
        User u= userRepository.findOne(dto.getId());
        User updated = getUser(dto, u);
        if(dto.getPasswordDigest() != null){
            u.setPassword(dto.getPasswordDigest());
        }

        updated = userRepository.save(updated);
        return fromEntity(updated);
    }

    @Override
    public UserDTO updatePasswordUser(UserPasswordDTO dto)  {
        log.info("Updating User password, UserId={}", dto.getId());
        User u= userRepository.findOne(dto.getId());
        u.setPassword(dto.getPasswordDigest());
        return fromEntity(u);
    }

    @Override
    public UserDetailsDTO getUserDetailsByUsername(String username)
    {
        log.info("Collecting user details by name {}", username);
        User u = userRepository.findByUserName(username);
        if(u == null) {
            log.info("User NOT found", username);
            return null;
        }
        Set<Role> roles = new HashSet<>();
        if(u.getRoles() != null) roles.addAll(u.getRoles());
        return new UserDetailsDTO(roles, u.getPassword(), u.getUserName());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User u = userRepository.findByUserName(username);
        return fromEntity(u);
    }

    @Override
    public List<UserDTO> getUserList() {
        log.info("Collecting all users");
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(this::fromEntity)
                .collect(toList());
    }

    @Override
    public UserDTO getUser(Long id) {
        return fromEntity(userRepository.findOne(id));
    }

    private User getUser(UserDTO dto, User u) {
        u.setUserName(dto.getUserName());
        u.setId(dto.getId());
        u.setComment(dto.getComment());
        u.setEmail(dto.getEmail());
        u.setFirstName(dto.getFirstName());
        u.setLastName(dto.getLastName());
        u.setRoles(dto.getRoles());
        if (dto.getCompanyId() != null) {
            Company company = companyRepository.findOne(dto.getCompanyId());
            u.setCompany(company);
        }
        return u;
    }

    /**
     * Quick converter implementation. Better use Dozer or something else
     * */
    private UserDTO fromEntity(User u) {
        if(u == null) return null;
        UserDTO dto = UserDTO.builder()
        .id(u.getId())
        .userName(u.getUserName())
        .comment(u.getComment())
        .email(u.getEmail())
        .firstName(u.getFirstName())
        .lastName(u.getLastName())
        .roles(u.getRoles() != null ? new HashSet<>(u.getRoles()) : null)
        .companyId(u.getCompany() != null ? u.getCompany().getId() : null)
                .build();

        return dto;
    }
}
