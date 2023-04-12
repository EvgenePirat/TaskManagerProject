package com.example.taskmanagerproject.service.notification;

import com.example.taskmanagerproject.entity.task.Task;
import com.example.taskmanagerproject.entity.task_user.TaskUser;
import com.example.taskmanagerproject.entity.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailSender {

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("y.y.brekhunchenko@student.khai.edu");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    public void getDateForPostEmail(Task task){
        Duration duration = Duration.between(LocalDateTime.now(), task.getDateFinishedTask());
        String emailTo = task.getUserList().stream().map(TaskUser::getUser).map(User::getUsername).findFirst().get();
        String subject = "You have not finished task "+task.getTitle();
        String body = "You need make himself task, with name "+task.getTitle()+", because time soon finished "+task.getDateFinishedTask()+"\n You have left "+duration.toHours()+" hours for finished";
        sendSimpleMessage(emailTo,subject,body);
    }
}
