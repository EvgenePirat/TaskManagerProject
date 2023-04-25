package com.example.taskmanagerproject.service.helper.notification;

import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.repository.TaskRepository;
import com.example.taskmanagerproject.service.helper.notification.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TimeNotificationService {

    private final EmailSender emailSender;

    private final TaskRepository taskRepository;

    @Scheduled(fixedDelay = 3600000)
    @Transactional
    public void processNotificationWithHour(){
        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            if(checkTaskFor(task,"hours") == true){
                emailSender.getDateForPostEmail(task);
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void processNotificationWithDay(){
        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            if(checkTaskFor(task,"days") == true){
                emailSender.getDateForPostEmail(task);
            }
        }
    }

    @Transactional
    public boolean checkTaskFor(Task task, String typeOperation){
        Duration duration = Duration.between(LocalDateTime.now(), task.getDateFinishedTask());
        if(duration.isNegative() == true){
            if(task.getStatus().equals("DONE") == false){
                task.setStatus("BACK");
                taskRepository.save(task);
                return false;
            }
        }
        if(typeOperation.equals("hours")){
            if(task.getLevelPriority().equals("LOW")){
                if(duration.toHours() >= 1 && duration.toHours() < 3) return true;
            }else if(task.getLevelPriority().equals("MEDIUM")) {
                if((duration.toHours() >= 1 && duration.toHours() <= 3) || (duration.toHours() == 12)) return true;
            }else {
                if((duration.toHours() > 1 && duration.toHours() <= 3)||(duration.toHours()>10 && duration.toHours() < 13) || (duration.toMinutes() >= 15 && duration.toMinutes() <= 50)) return true;
            }
        }else {
            if(task.getLevelPriority().equals("LOW")){
                if(duration.toDays() == 1) return true;
            }else if(task.getLevelPriority().equals("MEDIUM")){
                if(duration.toDays() == 1 || duration.toDays() == 2) return true;
            }else{
                if(duration.toDays() >= 1 && duration.toDays() <= 3) return true;
            }
        }
        return false;
    }
}
