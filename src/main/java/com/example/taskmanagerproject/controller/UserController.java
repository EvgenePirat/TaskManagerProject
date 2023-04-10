package com.example.taskmanagerproject.controller;

import com.example.taskmanagerproject.dto.mappers.TaskMapper;
import com.example.taskmanagerproject.dto.mappers.UserMapper;
import com.example.taskmanagerproject.dto.model.TaskDto;
import com.example.taskmanagerproject.dto.model.UserDto;
import com.example.taskmanagerproject.dto.validation.OnCreate;
import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.service.TaskService;
import com.example.taskmanagerproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    private final TaskService taskService;


    @PutMapping("/{id}")
    public String savePhotoForUser(@PathVariable Long id, @RequestParam("image") MultipartFile photo){
        return userService.savePhotoInUser(photo,id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id){
        User user = userService.getById(id);
        return UserMapper.changeFromUserToUserDto(user);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTaskByUserId(@PathVariable Long id){
        List<Task> taskList = taskService.getAllByUser(id);
        return TaskMapper.changeListTaskToTaskDro(taskList);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto taskDto){
        Task task = TaskMapper.changeFromTaskDtoToTask(taskDto);
        Task taskCreate = taskService.create(task,id);
        return TaskMapper.changeTaskToTaskDro(taskCreate);
    }
}
