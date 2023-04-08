package com.example.taskmanagerproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Data
public class TaskUserKey implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "task_id",nullable = false)
    private Long taskId;
}
