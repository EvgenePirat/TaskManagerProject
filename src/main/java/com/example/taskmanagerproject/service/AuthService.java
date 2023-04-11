package com.example.taskmanagerproject.service;

import com.example.taskmanagerproject.dto.auth.JwtRequest;
import com.example.taskmanagerproject.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
