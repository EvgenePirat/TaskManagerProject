package com.example.taskmanagerproject.entity.task_user;

import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("taskId")
    @JoinColumn(name = "task_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

}
