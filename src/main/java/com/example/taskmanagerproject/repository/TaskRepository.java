package com.example.taskmanagerproject.repository;

import com.example.taskmanagerproject.entity.task.Task;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepository {

    Task findByTitle(String title);

    Set<Task> findByLevelPriority(String LevelPriority);
}
