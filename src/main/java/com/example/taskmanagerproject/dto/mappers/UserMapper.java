package com.example.taskmanagerproject.dto.mappers;

import com.example.taskmanagerproject.dto.model.UserDto;
import com.example.taskmanagerproject.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserMapper {

    public static UserDto changeFromUserToUserDto(User user){
        return new UserDto(user.getId(),user.getUsername(),user.getName(),user.getPassword(), user.getPathPhoto(), user.getUserList(),user.getRoles());
    }

    public static User changeFromUserDtoToUser(UserDto userDto){
        return new User(userDto.getId(),userDto.getUsername(),userDto.getName(),userDto.getPassword(), userDto.getPathPhoto(), userDto.getRoles(),userDto.getUserList());
    }

}
