package com.hoaxify.ws.user.controller;


import com.hoaxify.ws.exception.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import com.hoaxify.ws.user.model.User;
import com.hoaxify.ws.user.repository.UserRepository;
import com.hoaxify.ws.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @PostMapping("/api/1.0/users")
//    @ResponseStatus(HttpStatus.CREATED)  201 created mesajı verir
    public GenericResponse createUser(@Valid @RequestBody User user){

        userService.save(user);
        return new GenericResponse("user created");

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception){
        ApiError error = new ApiError(400,"Validation Error","/api/1.0/users");
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return error;
    }
}
