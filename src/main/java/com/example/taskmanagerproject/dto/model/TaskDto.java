package com.example.taskmanagerproject.dto.model;

import com.example.taskmanagerproject.service.helper.validation.OnCreate;
import com.example.taskmanagerproject.service.helper.validation.OnUpdate;
import com.example.taskmanagerproject.entity.task.LevelPriority;
import com.example.taskmanagerproject.entity.task.Status;
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
    private LevelPriority levelPriority;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm")
    @Future(message = "Date must be future than date now!", groups = OnCreate.class)
    @NotNull(message = "Date must be not null", groups = OnCreate.class)
    private LocalDateTime dateFinishedTask;

    @NotNull(message = "Status must be not null!", groups = {OnCreate.class, OnUpdate.class})
    private Status status;
}
