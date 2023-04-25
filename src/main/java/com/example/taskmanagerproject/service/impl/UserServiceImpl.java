package com.example.taskmanagerproject.service.impl;

import com.example.taskmanagerproject.entity.task_user.TaskUser;
import com.example.taskmanagerproject.entity.task_user.TaskUserKey;
import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.exception.ResourcesNotFoundException;
import com.example.taskmanagerproject.repository.UserRepository;
import com.example.taskmanagerproject.service.UserService;
import com.example.taskmanagerproject.service.helper.notification.GreetingNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final GreetingNotificationService greetingNotificationService;

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new ResourcesNotFoundException("User not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new ResourcesNotFoundException("User not found!"));
    }

    @Override
    @Transactional
    public String savePhotoInUser(MultipartFile photo, Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourcesNotFoundException("User not found!"));
        String pathPhoto = savePhotoOnCatalog(photo,user);
        user.setPathPhoto(pathPhoto);
        return pathPhoto;
    }

    private String savePhotoOnCatalog(MultipartFile photo, User user){
        try{
            byte[] photoBytes = photo.getBytes();
            Path pathPhoto = Paths.get("D:\\ManeProject\\ManagerTaskProject\\TaskManagerProject\\src\\main\\resources\\photoBD", "photo"+user.getId()+".jpg");
            Files.write(pathPhoto, photoBytes);
            return "D:\\ManeProject\\ManagerTaskProject\\TaskManagerProject\\src\\main\\resources\\photoBD\\photo"+user.getId()+".jpg";
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public User create(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).orElse(null);
        if(userFromDb != null){
            throw new IllegalStateException("User already exist!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        greetingNotificationService.processGreetingWithNewUser(user);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean isTaskOwner(Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourcesNotFoundException("User not found!"));
        return user.getUserList().stream().map(TaskUser::getId).map(TaskUserKey::getTaskId).anyMatch((id)->id == taskId);
    }
}
