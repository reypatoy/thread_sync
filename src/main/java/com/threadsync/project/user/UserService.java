package com.threadsync.project.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.threadsync.project.configuration.JWTService;
import com.threadsync.project.exceptionHandler.UnAuthorizedException;
import com.threadsync.project.request.UserRequest;
import com.threadsync.project.response.ListResponse;
import com.threadsync.project.response.UserResponse;
import com.threadsync.project.response.UserResponse.Search;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserResponse.Register> register(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            userRepository.save(user);
            return ResponseEntity.ok().body(UserResponse.Register.builder().succeeded(true).msg("Successfully save!!").build());

        }catch(DataIntegrityViolationException  e){

            return ResponseEntity.badRequest().body(UserResponse.Register.builder().msg("Email already exist!").build());
        }
    }

    public ResponseEntity<ListResponse<Search>> search(UserRequest.Search selector) {
        Pageable pageable = PageRequest.of(selector.getOffset(), selector.getSize());
        List<User> users = userRepository.search(selector.getLastName(), selector.getFirstName(), selector.getEmail(), pageable);
        long total = userRepository.total(selector.getLastName(), selector.getFirstName(), selector.getEmail());
        if(users.isEmpty()){
            return ResponseEntity.ok().body(ListResponse.build(0,selector.getOffset(), List.of()));
        }
        return ResponseEntity.ok().body(ListResponse.build(total, selector.getOffset(), users.stream().map(User::toSearch).toList()));
    }

    public AuthenticationResponse login(UserRequest.Login request) throws UnAuthorizedException {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword())
            );
            Optional<User> existingUser = userRepository.getByEmail(request.getEmail());
            String token = jwtService.generateToken(existingUser.get());
            String refreshToken = jwtService.generateRefreshToken(existingUser.get());
            return AuthenticationResponse.builder().token(token).refreshToken(refreshToken).build();
        }catch(Exception e){
            throw new UnAuthorizedException("Invalid credentials");
        }
    }
}
