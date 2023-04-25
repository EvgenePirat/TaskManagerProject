package com.example.taskmanagerproject.service;

import com.example.taskmanagerproject.dto.auth_model.JwtRequest;
import com.example.taskmanagerproject.dto.auth_model.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
