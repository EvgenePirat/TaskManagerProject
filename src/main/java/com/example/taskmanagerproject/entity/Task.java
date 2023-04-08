package com.example.taskmanagerproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",length = 800)
    private String description;

    @Column(name = "level_priority", nullable = false,columnDefinition = "VARCHAR(255)")
    private LevelPriority levelPriority;

    @Column(name = "finished_date", nullable = false)
    private LocalDateTime dateFinishedTask;

    @Column(name = "status", nullable = false,columnDefinition = "VARCHAR(255)")
    private Status status;

    @OneToMany(mappedBy = "task")
    private List<TaskUser> userList;
}
