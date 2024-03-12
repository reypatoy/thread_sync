package com.threadsync.project.response;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponse {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Register {
        private boolean succeeded;
        private String msg;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Search {

        private String id;
        private String firstName;
        private String lastName;
        private String email;
    }
}
