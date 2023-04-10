package com.example.taskmanagerproject.entity.task_user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserKey implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "task_id",nullable = false)
    private Long taskId;
}
