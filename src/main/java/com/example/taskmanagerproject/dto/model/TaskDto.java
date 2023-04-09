package com.example.taskmanagerproject.dto.model;

import com.example.taskmanagerproject.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private String levelPriority;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime dateFinishedTask;

    private String status;

    public static TaskDto changeTaskToTaskDro(Task task){
        return new TaskDto(task.getId(),task.getTitle(),task.getDescription(),task.getLevelPriority().name(),task.getDateFinishedTask(),task.getStatus().name());
    }
}
