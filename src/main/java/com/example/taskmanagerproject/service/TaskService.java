package com.example.taskmanagerproject.service;

import com.example.taskmanagerproject.entity.task.Status;
import com.example.taskmanagerproject.entity.task.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllByUser(Long userId);

    Task getById(Long taskId);

    void delete(Long id);

    Task update(Task task);

    Task create(Task task, Long id);

    Task foundTaskWithTitle(String title, Long userId);

    List<Task> foundTasksWithStatus(String status, Long userId);

    List<Task> foundTasksWithLevelPriority(String level_priority, Long userId);

    List<Task> foundTasksWithDate(String date, Long userId);
}
