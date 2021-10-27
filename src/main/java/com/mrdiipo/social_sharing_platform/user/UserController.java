package com.mrdiipo.social_sharing_platform.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(path = "/api/1.0/users")
    void createUser(){

    }
}
