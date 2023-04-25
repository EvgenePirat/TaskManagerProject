package com.example.taskmanagerproject.service.helper.notification;

import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.service.helper.notification.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GreetingNotificationService {

    private final EmailSender emailSender;

    public void processGreetingWithNewUser(User user){
        emailSender.createEmailForGreeting(user);
    }
}
