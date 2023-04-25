package com.example.taskmanagerproject.dto.auth_model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "request for login")
public class JwtRequest {
    @NotNull(message = "Username must be not null")
    @Schema(description = "email", example = "eugene.brexyn@gmail.com")
    private String username;

    @Schema(description = "password",example = "1313")
    @NotNull(message = "Password must be not null")
    private String password;
}
