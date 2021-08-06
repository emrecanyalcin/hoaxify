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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @PostMapping("/api/1.0/users")
//    @ResponseStatus(HttpStatus.CREATED)  201 created mesajı verir
    public ResponseEntity<?> createUser(@RequestBody User user){

        ApiError error = new ApiError(400,"Validation Error","/api/1.0/users");
        Map<String, String> validationErrors = new HashMap<>();

        String username = user.getUsername();
        String displayName = user.getDisplayName();

        if (username == null || username.isEmpty()){
            validationErrors.put("username","Username can not be null");
        }

        if (displayName == null || displayName.isEmpty()){
            validationErrors.put("displayName","Can not be null");
        }

        if (validationErrors.size() > 0){
            error.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        userService.save(user);
        return ResponseEntity.ok(new GenericResponse("user created"));

    }
}
