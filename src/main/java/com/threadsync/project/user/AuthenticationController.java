package com.threadsync.project.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.threadsync.project.exceptionHandler.BadRequestException;
import com.threadsync.project.exceptionHandler.UnAuthorizedException;
import com.threadsync.project.request.UserRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> postMethodName(@Valid @RequestBody UserRequest.Login request, BindingResult result) throws UnAuthorizedException {
        if(result.hasErrors()) {
            System.out.println("niabot diri");
            throw new BadRequestException("Please provide credentials");
        }
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK) ;
    }
}
