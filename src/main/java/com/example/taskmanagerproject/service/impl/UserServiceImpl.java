package com.example.taskmanagerproject.service.impl;

import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.exception.ResourcesNotFoundException;
import com.example.taskmanagerproject.repository.UserRepository;
import com.example.taskmanagerproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User update(User user) {
        return null;
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
    public User create(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).orElse(null);
        if(userFromDb != null){
            throw new IllegalStateException("User already exist!");
        }
        return userRepository.save(user);
    }
}
