package com.example.taskmanagerproject.service;

import com.example.taskmanagerproject.entity.user.User;

public interface UserService {

    User update(User user);

    void delete(Long id);

    User getById(Long id);

    User getByUsername(String username);

    User create(User user);
}
