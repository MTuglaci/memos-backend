package com.memos.backend.controllers;

import com.memos.backend.errors.ApiError;
import com.memos.backend.models.UserTable;
import com.memos.backend.services.UserSerivice;
import com.memos.backend.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class UserController {

    private static final Logger Log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserSerivice userSerivice;

    @PostMapping("/api/1.0/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserTable user) throws IllegalAccessException {
//        String username = user.getUsername();
//        if (username == null || username.isEmpty()) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(new ApiError(400,"Validation error","/api/1.0/users",
//                            new HashMap<String, String>() {{ put("username", "Username cannot be null"); }}
//                            )
//                    );
//        }
//
//        Map<String, String> validationErrors = user.isValid();
//        if (!validationErrors.isEmpty()) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(new ApiError(400,"Validation error","/api/1.0/users", validationErrors));
//        }

        userSerivice.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse("User successfully created."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        return new ApiError(400,"Validation error","/api/1.0/users",
                new HashMap<String, String>() {{
                    for (FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
                        put(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                }}
        );
    }
}
