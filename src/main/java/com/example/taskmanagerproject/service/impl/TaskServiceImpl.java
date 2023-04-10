package com.example.taskmanagerproject.service.impl;

import com.example.taskmanagerproject.entity.task.Status;
import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.entity.task_user.TaskUser;
import com.example.taskmanagerproject.entity.task_user.TaskUserKey;
import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.exception.ResourcesNotFoundException;
import com.example.taskmanagerproject.repository.TaskRepository;
import com.example.taskmanagerproject.repository.TasksUserRepository;
import com.example.taskmanagerproject.repository.UserRepository;
import com.example.taskmanagerproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TasksUserRepository tasksUserRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourcesNotFoundException("User not found!"));
        List<TaskUser> userList = user.getUserList();
        return userList.stream()
                .map(TaskUser::getId)
                .map(TaskUserKey::getTaskId)
                .map((id)->taskRepository.findById(id).get())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(()->new ResourcesNotFoundException("Task not found!"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Task task = taskRepository.findById(id).get();
        taskRepository.delete(task);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        taskRepository.findById(task.getId()).orElseThrow(()->new ResourcesNotFoundException("Task for update not found"));
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task create(Task task, Long id) {
        task = taskRepository.save(task);
        TaskUser taskUser = new TaskUser(new TaskUserKey(id,task.getId()),userRepository.findById(id).orElseThrow(()->new ResourcesNotFoundException("User not found!")),task);
        tasksUserRepository.save(taskUser);
        return task;
    }
}
