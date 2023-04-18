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

import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public Task foundTaskWithTitle(String title, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourcesNotFoundException("User not found!"));
        return user.getUserList().stream()
                .map(TaskUser::getId)
                .map(TaskUserKey::getTaskId)
                .map((id)->taskRepository.findById(id).get())
                .filter(task -> task.getTitle().equals(title))
                .findFirst().orElseThrow(()->new ResourcesNotFoundException("Task not found!"));
    }

    @Override
    @Transactional
    public List<Task> foundTasksWithStatus(String status, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourcesNotFoundException("User not found!"));
        return user.getUserList().stream()
                .map(TaskUser::getId)
                .map(TaskUserKey::getTaskId)
                .map((id)->taskRepository.findById(id).get())
                .filter(task -> task.getStatus().equals(status))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Task> foundTasksWithLevelPriority(String level_priority, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourcesNotFoundException("User not found!"));
        return user.getUserList().stream()
                .map(TaskUser::getId)
                .map(TaskUserKey::getTaskId)
                .map((id)->taskRepository.findById(id).get())
                .filter(task -> task.getLevelPriority().equals(level_priority))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> foundTasksWithDate(String date, Long userId) {
        String[] parts = date.split("[\\s-:]");
        LocalDateTime localDateTimeFromUser = LocalDateTime.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4]));
        User user = userRepository.findById(userId).orElseThrow(()->new ResourcesNotFoundException("User not found!"));
        return user.getUserList().stream()
                .map(TaskUser::getId)
                .map(TaskUserKey::getTaskId)
                .map((id)->taskRepository.findById(id).get())
                .filter(task -> task.getDateFinishedTask().getDayOfYear() == localDateTimeFromUser.getDayOfYear())
                .distinct()
                .collect(Collectors.toList());
    }

}
