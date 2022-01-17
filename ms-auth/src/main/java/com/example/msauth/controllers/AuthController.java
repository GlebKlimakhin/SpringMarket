package com.example.msauth.controllers;


import com.example.corelib.models.UserInfo;
import com.example.corelib.repositories.RedisRepository;
import com.example.corelib.services.ITokenService;
import com.example.msauth.dtos.AuthRequestDto;
import com.example.msauth.dtos.AuthResponseDto;
import com.example.msauth.dtos.SignUpRequestDto;
import com.example.msauth.entities.User;
import com.example.msauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService iTokenService;

    @Autowired
    private RedisRepository redisReposotory;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> {
            roles.add(role.getName());
        });
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getEmail())
                .roles(roles)
                .build();
        String token = iTokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @GetMapping("/logout")
    public Boolean logout(@RequestHeader("Authorization") String authorization) {
        redisReposotory.saveToken(authorization);
        return true;
    }
}
