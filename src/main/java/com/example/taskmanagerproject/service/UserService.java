package com.example.taskmanagerproject.service;

import com.example.taskmanagerproject.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User update(User user);

    void delete(Long id);

    User getById(Long id);

    User getByUsername(String username);

    String savePhotoInUser(MultipartFile photo, Long id);

    User create(User user);

    boolean isTaskOwner(Long userId, Long taskId);
}
