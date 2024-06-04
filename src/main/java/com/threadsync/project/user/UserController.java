package com.threadsync.project.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.threadsync.project.request.UserRequest;
import com.threadsync.project.response.ListResponse;
import com.threadsync.project.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse.Register> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/list")
    public ResponseEntity<ListResponse<UserResponse.Search>> search(@RequestBody UserRequest.Search selector) {
        return userService.search(selector);
    }
}