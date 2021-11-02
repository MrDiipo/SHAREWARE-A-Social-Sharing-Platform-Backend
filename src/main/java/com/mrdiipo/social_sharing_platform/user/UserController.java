package com.mrdiipo.social_sharing_platform.user;

import com.mrdiipo.social_sharing_platform.error.ApiError;
import com.mrdiipo.social_sharing_platform.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired UserService userService;

    @PostMapping(path = "/api/1.0/users")
     GenericResponse createUser( @Validated @RequestBody User user){
        userService.save(user);
        return new GenericResponse("set_message");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(400, "validation error", request.getServletPath());

        BindingResult result = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError error : result.getFieldErrors()){
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }
        apiError.setValidationError(validationErrors);

        return apiError;
    }
}
