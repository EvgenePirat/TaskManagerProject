package com.example.taskmanagerproject.entity.task_user;

import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUser {

    @EmbeddedId
    private TaskUserKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id",nullable = false)
    private Task task;
}
