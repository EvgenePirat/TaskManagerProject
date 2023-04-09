package com.example.taskmanagerproject.dto.model;

import com.example.taskmanagerproject.dto.validation.OnCreate;
import com.example.taskmanagerproject.dto.validation.OnUpdate;
import com.example.taskmanagerproject.entity.user.Role;
import com.example.taskmanagerproject.entity.task_user.TaskUser;
import com.example.taskmanagerproject.entity.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Id must be not null!", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Username must be not null!", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 500, message = "Username must be smaller than 500 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @NotNull(message = "Name must be not null!", groups = {OnUpdate.class})
    @Length(max = 255, message = "Name must be smaller than 255 symbols", groups = {OnUpdate.class})
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null!", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 500, message = "Password must be smaller than 500 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Length(max = 500, message = "pathPhoto must be smaller than 500 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String pathPhoto;

    private List<TaskUser> userList;

    private Set<Role> roles;

    public static UserDto changeFromUserToUserDto(User user){
        return new UserDto(user.getId(),user.getUsername(),user.getName(),user.getPassword(),user.getPathPhoto(),user.getUserList(),user.getRoles());
    }
}
