package com.mrdiipo.social_sharing_platform.user;

import com.mrdiipo.social_sharing_platform.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
public class LoginController {

    @PostMapping(path = "/api/1.0/login")
    void handleLogin(){

    }

}
