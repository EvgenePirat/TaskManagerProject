package com.example.taskmanagerproject.security.jwt;

import com.example.taskmanagerproject.entity.user.User;
import com.example.taskmanagerproject.security.jwt.JwtEntityFactory;
import com.example.taskmanagerproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        UserDetails userDetails = JwtEntityFactory.create(user);
        return userDetails;
    }
}
