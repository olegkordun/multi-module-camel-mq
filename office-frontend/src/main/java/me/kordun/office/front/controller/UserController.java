package me.kordun.office.front.controller;


import me.kordun.json.UserJson;
import me.kordun.office.front.service.UserFrontService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger("UserController");

    @Autowired
    private UserFrontService userFrontService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserJson create( @RequestBody @Valid UserJson createJson)
            throws MethodArgumentNotValidException, BindException {
        log.info("Got user create request");
        return userFrontService.createUser(createJson);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public UserJson update(@PathVariable(value = "id") Long id,
                                     @RequestBody @Valid UserJson userJson)
            throws MethodArgumentNotValidException, BindException {
        log.info("Got user update id={}", id);
        userJson.setId(id);
        return userFrontService.updateUser(userJson);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserJson get(@PathVariable(value = "id") Long id)
            throws MethodArgumentNotValidException, BindException {
        log.info("Got user request id={}", id);

        return userFrontService.getUser(id);
    }

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserJson> getList()
            throws MethodArgumentNotValidException, BindException {
        log.info("Get all users");

        return userFrontService.getAllUsers();
    }

    @RequestMapping(value = "/users/{id}/password", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable(value = "id") Long id,
                               @RequestParam String passwordDigest)
            throws MethodArgumentNotValidException, BindException {
        log.info("Got user password update userId={}", id);
        userFrontService.updatePassword(id, passwordDigest);
    }
}
