package com.example.taskmanagerproject.controller;

import com.example.taskmanagerproject.entity.mappers.TaskMapper;
import com.example.taskmanagerproject.entity.mappers.UserMapper;
import com.example.taskmanagerproject.dto.model.TaskDto;
import com.example.taskmanagerproject.dto.model.UserDto;
import com.example.taskmanagerproject.service.helper.validation.OnCreate;
import com.example.taskmanagerproject.service.helper.validation.OnUpdate;
import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.service.TaskService;
import com.example.taskmanagerproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;

    private final TaskService taskService;


    @PutMapping("/{id}")
    @Operation(summary = "save avatar for user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public String savePhotoForUser(@PathVariable Long id, @RequestParam("image") MultipartFile photo){
        return userService.savePhotoInUser(photo,id);
    }

    @PutMapping
    @Operation(summary = "update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userDto.id)")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto){
        User user = UserMapper.changeFromUserDtoToUser(userDto);
        User userAfterUpdate = userService.update(user);
        return UserMapper.changeFromUserToUserDto(userAfterUpdate);
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "delete user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable long id){
        User user = userService.getById(id);
        return UserMapper.changeFromUserToUserDto(user);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "get all tasks for user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDto> getTaskByUserId(@PathVariable Long id){
        List<Task> taskList = taskService.getAllByUser(id);
        return TaskMapper.changeListTaskToTaskDro(taskList);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "create task")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto taskDto){
        Task task = TaskMapper.changeFromTaskDtoToTask(taskDto);
        Task taskCreate = taskService.create(task,id);
        return TaskMapper.changeTaskToTaskDro(taskCreate);
    }
}
