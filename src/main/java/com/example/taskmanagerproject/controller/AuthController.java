package com.example.taskmanagerproject.controller;

import com.example.taskmanagerproject.dto.auth.JwtRequest;
import com.example.taskmanagerproject.dto.auth.JwtResponse;
import com.example.taskmanagerproject.dto.mappers.UserMapper;
import com.example.taskmanagerproject.dto.model.UserDto;
import com.example.taskmanagerproject.dto.validation.OnCreate;
import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.service.AuthService;
import com.example.taskmanagerproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto){
        User user = UserMapper.changeFromUserDtoToUser(userDto);
        User createUser = userService.create(user);
        return UserMapper.changeFromUserToUserDto(createUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }
}
