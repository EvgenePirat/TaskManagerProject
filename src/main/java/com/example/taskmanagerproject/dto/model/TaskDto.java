package com.example.taskmanagerproject.dto.model;

import com.example.taskmanagerproject.dto.validation.OnCreate;
import com.example.taskmanagerproject.dto.validation.OnUpdate;
import com.example.taskmanagerproject.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskDto {

    @Positive(message = "Id must be positive number!",groups = OnUpdate.class)
    @NotNull(message = "Id task must be not null!", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title must be not null!", groups = OnCreate.class)
    private String title;

    @Length(max = 800, message = "Description must be smaller than 800 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @NotNull(message = "Level priority must be not null!", groups = {OnCreate.class, OnUpdate.class})
    private String levelPriority;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    @Future(message = "Date must be future than date now!", groups = OnCreate.class)
    private LocalDateTime dateFinishedTask;

    @NotNull(message = "Status must be not null!", groups = OnCreate.class)
    private String status;

    public static TaskDto changeTaskToTaskDro(Task task){
        return new TaskDto(task.getId(),task.getTitle(),task.getDescription(),task.getLevelPriority().name(),task.getDateFinishedTask(),task.getStatus().name());
    }
}
