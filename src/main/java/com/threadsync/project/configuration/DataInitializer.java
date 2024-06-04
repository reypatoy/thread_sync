package com.threadsync.project.configuration;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.threadsync.project.user.Role;
import com.threadsync.project.user.User;
import com.threadsync.project.user.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DataInitializer {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUser(){
        String defaultUserEmail = "root@gmail.com";
        Optional<User> defaultOldUser = userRepository.getByEmail(defaultUserEmail);
        if(!defaultOldUser.isPresent()){
            User defaultUser = User.builder()
                                .id(UUID.randomUUID().toString())
                                .firstName("rootFirstname")
                                .lastName("rootLastname")
                                .email(defaultUserEmail)
                                .password(passwordEncoder.encode("root@123"))
                                .role(Role.USER)
                                .build();
            userRepository.save(defaultUser);
        }
    }
}
