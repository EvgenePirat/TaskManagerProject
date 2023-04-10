package com.example.taskmanagerproject.dto.model;

import com.example.taskmanagerproject.dto.validation.OnCreate;
import com.example.taskmanagerproject.dto.validation.OnUpdate;
import com.example.taskmanagerproject.entity.user.Role;
import com.example.taskmanagerproject.entity.task_user.TaskUser;
import com.example.taskmanagerproject.entity.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    public UserDto(Long id, String username, String name, String password, String pathPhoto, List<TaskUser> userList, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.pathPhoto = pathPhoto;
        this.userList = userList;
        this.roles = roles;
    }

    @Positive(message = "Id must be positive number!",groups = OnUpdate.class)
    @NotNull(message = "Id must be not null!", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Username must be not null!", groups = OnCreate.class)
    @Email(message = "Username must be email!", groups = OnCreate.class)
    @Length(max = 500, message = "Username must be smaller than 500 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Length(max = 255, message = "Name must be smaller than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null!", groups = OnCreate.class)
    @Length(max = 500, message = "Password must be smaller than 500 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String password;


    private MultipartFile photo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pathPhoto;

    private List<TaskUser> userList;

    @NotEmpty(message = "User can have role!", groups = OnCreate.class)
    private Set<Role> roles;

}