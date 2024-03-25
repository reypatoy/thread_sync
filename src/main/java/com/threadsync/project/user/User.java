package com.threadsync.project.user;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import com.threadsync.project.response.UserResponse;
import com.threadsync.project.response.UserResponse.Login;
import com.threadsync.project.response.UserResponse.Search;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String lastName;

    @NotBlank
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String password;

    public static Search toSearch(User user) {
        return Search.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static Login toLoginResponse(User user, String token) {
        return Login.builder().message("Login Succeessful")
                .succeeded(true).user(
                                                UserResponse.User.builder()
                                                .id(user.getId())
                                                .firstName(user.getFirstName())
                                                .lastName(user.getLastName())
                                                .email(user.getEmail())
                                                .token(token)
                                                .build()
                                                ).build();
    }
}