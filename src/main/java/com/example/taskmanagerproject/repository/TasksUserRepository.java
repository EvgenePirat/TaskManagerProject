package com.example.taskmanagerproject.repository;

import com.example.taskmanagerproject.entity.task_user.TaskUser;
import com.example.taskmanagerproject.entity.task_user.TaskUserKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksUserRepository extends JpaRepository<TaskUser, TaskUserKey> {
}
