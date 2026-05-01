package com.irfan.smartstudy.controller;

import com.irfan.smartstudy.dto.SignupRequest;
import com.irfan.smartstudy.model.User;
import com.irfan.smartstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }
}
