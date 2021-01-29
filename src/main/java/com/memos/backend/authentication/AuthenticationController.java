package com.memos.backend.authentication;

import com.fasterxml.jackson.annotation.JsonView;
import com.memos.backend.shared.CurrentUser;
import com.memos.backend.shared.Views;
import com.memos.backend.user.User;
import com.memos.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/1.0/auth")
    @JsonView(Views.Base.class)
    ResponseEntity handleAuthentication(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }
}
