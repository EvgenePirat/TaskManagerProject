package com.example.taskmanagerproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TaskManagerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerProjectApplication.class, args);
    }

}
