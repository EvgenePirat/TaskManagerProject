package com.example.taskmanagerproject.entity.task;

import com.example.taskmanagerproject.entity.task_user.TaskUser;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    public Task() {}

    public Task(Long id, String title, String description, String levelPriority, LocalDateTime dateFinishedTask, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.levelPriority = levelPriority;
        this.dateFinishedTask = dateFinishedTask;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",length = 800)
    private String description;

    @Column(name = "level_priority", nullable = false)
    private String levelPriority;

    @Column(name = "finished_date", nullable = false)
    private LocalDateTime dateFinishedTask;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TaskUser> userList;

}
