package org.sopt.diary.api;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.request.LoginRequest;
import org.sopt.diary.api.dto.request.SignupRequest;
import org.sopt.diary.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/signup")
    ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest signupRequest) {
        userService.signUp(signupRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("api/login")
    ResponseEntity<Void> login(@Valid @RequestBody LoginRequest loginRequest) {
        userService.logIn(loginRequest);
        return ResponseEntity.ok().build();

    }



}
