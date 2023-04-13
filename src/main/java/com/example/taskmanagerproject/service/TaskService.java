package com.example.taskmanagerproject.service;

import com.example.taskmanagerproject.entity.task.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllByUser(Long userId);

    Task getById(Long taskId);

    void delete(Long id);

    Task update(Task task);

    Task create(Task task, Long id);

}
