package com.mrdiipo.social_sharing_platform.user;

import com.mrdiipo.social_sharing_platform.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired UserService userService;

    @PostMapping(path = "/api/1.0/users")
     GenericResponse createUser(@Validated @RequestBody User user){

        userService.save(user);
        return new GenericResponse("set_message");
    }
}
