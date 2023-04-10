package com.example.taskmanagerproject.entity.user;

import com.example.taskmanagerproject.entity.task_user.TaskUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    public User() {
    }

    public User(Long id, String username, String name, String password, String pathPhoto, Set<Role> roles, List<TaskUser> userList) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.pathPhoto = pathPhoto;
        this.roles = roles;
        this.userList = userList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 500)
    private String username;

    @Column(name = "full_name")
    private String name;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "photo_path", length = 500)
    private String pathPhoto;

    @Column(name = "date_create")
    private LocalDateTime timeCreateAccount;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TaskUser> userList;

    @PrePersist
    public void initDateCreateUser() {
        timeCreateAccount = LocalDateTime.now();
    }

}
