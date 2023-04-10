package com.example.taskmanagerproject.controller;

import com.example.taskmanagerproject.dto.mappers.TaskMapper;
import com.example.taskmanagerproject.dto.model.TaskDto;
import com.example.taskmanagerproject.dto.validation.OnUpdate;
import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long taskId){
        Task task = taskService.getById(taskId);
        return TaskMapper.changeTaskToTaskDro(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        taskService.delete(id);
    }

    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto){
        Task task = TaskMapper.changeFromTaskDtoToTask(taskDto);
        Task taskReturned = taskService.update(task);
        return TaskMapper.changeTaskToTaskDro(taskReturned);
    }
}
