package com.example.taskmanagerproject.service.notification;

import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final EmailSender emailSender;

    private final TaskRepository taskRepository;

    @Scheduled(fixedDelay = 3600000)
    @Transactional
    public void processNotification(){
        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            if(checkTask(task) == true){
                emailSender.getDateForPostEmail(task);
            }
        }
    }

    @Transactional
    public boolean checkTask(Task task){
        Duration duration = Duration.between(LocalDateTime.now(), task.getDateFinishedTask());
        if(duration.isNegative() == true){
            if(task.getStatus().equals("DONE") == false){
                task.setStatus("BACK");
                taskRepository.save(task);
                return false;
            }
        }
        if(task.getStatus().equals("ACTIVE") == false){
            return false;
        }
        if(duration.toHours() > 1 && duration.toHours() < 5){
            return true;
        }
        return false;
    }


}
