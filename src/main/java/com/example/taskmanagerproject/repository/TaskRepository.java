package com.example.taskmanagerproject.repository;

import com.example.taskmanagerproject.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Task findByTitle(String title);

    Set<Task> findByLevelPriority(String LevelPriority);
}
