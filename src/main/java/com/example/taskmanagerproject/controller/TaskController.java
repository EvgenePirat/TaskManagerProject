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

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/filter/with_title/{title}/{id}")
    @Operation(summary = "found task with title")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public TaskDto foundTaskWithTitle(@PathVariable String title, @PathVariable Long id){
        Task task = taskService.foundTaskWithTitle(title,id);
        return TaskMapper.changeTaskToTaskDro(task);
    }

    @GetMapping("/filter/with_status/{status}/{id}")
    @Operation(summary = "found tasks with status")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public List<TaskDto> foundTasksWithStatus(@PathVariable String status, @PathVariable Long id){
        List<Task> taskList = taskService.foundTasksWithStatus(status,id);
        return TaskMapper.changeListTaskToTaskDro(taskList);
    }

    @GetMapping("/filter/with_level_priority/{level}/{id}")
    @Operation(summary = "found tasks with level priority")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public List<TaskDto> foundTasksWithLevelPriority(@PathVariable String level, @PathVariable Long id){
        List<Task> taskList = taskService.foundTasksWithLevelPriority(level,id);
        return TaskMapper.changeListTaskToTaskDro(taskList);
    }

    @GetMapping("/filter/with_date/{date}/{id}")
    @Operation(summary = "found tasks with date")
    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public List<TaskDto> foundTasksWithDate(@PathVariable String date, @PathVariable Long id){
        List<Task> taskList = taskService.foundTasksWithDate(date,id);
        return TaskMapper.changeListTaskToTaskDro(taskList);
    }
}
