package com.example.taskmanagerproject.dto.mappers;

import com.example.taskmanagerproject.dto.model.TaskDto;
import com.example.taskmanagerproject.entity.task.LevelPriority;
import com.example.taskmanagerproject.entity.task.Status;
import com.example.taskmanagerproject.entity.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskDto changeTaskToTaskDro(Task task){
        return new TaskDto(task.getId(),task.getTitle(),task.getDescription(),LevelPriority.valueOf(task.getLevelPriority()),task.getDateFinishedTask(),Status.valueOf(task.getStatus()));
    }

    public static Task changeFromTaskDtoToTask(TaskDto taskDto){
        return new Task(taskDto.getId(),taskDto.getTitle(),taskDto.getDescription(),taskDto.getLevelPriority().name(),taskDto.getDateFinishedTask(),taskDto.getStatus().name());
    }

    public static List<TaskDto> changeListTaskToTaskDro(List<Task> taskList){
        List<TaskDto> taskDtoList = new ArrayList<>();
        for(Task task : taskList){
            taskDtoList.add(TaskMapper.changeTaskToTaskDro(task));
        }
        return taskDtoList;
    }
}
