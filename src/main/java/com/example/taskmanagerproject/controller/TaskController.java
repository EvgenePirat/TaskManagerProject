package com.example.taskmanagerproject.controller;

import com.example.taskmanagerproject.dto.mappers.TaskMapper;
import com.example.taskmanagerproject.dto.model.TaskDto;
import com.example.taskmanagerproject.dto.validation.OnUpdate;
import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Validated
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    @Operation(summary = "get task")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public TaskDto getById(@PathVariable Long id){
        Task task = taskService.getById(id);
        return TaskMapper.changeTaskToTaskDro(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete task")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public void deleteById(@PathVariable Long id){
        taskService.delete(id);
    }

    @PutMapping
    @Operation(summary = "update task")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#taskDto.id)")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto){
        Task task = TaskMapper.changeFromTaskDtoToTask(taskDto);
        Task taskReturned = taskService.update(task);
        return TaskMapper.changeTaskToTaskDro(taskReturned);
    }
}
