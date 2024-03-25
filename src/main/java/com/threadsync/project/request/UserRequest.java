package com.threadsync.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest extends BaseSelector {
    
   
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Search extends BaseSelector implements Operrands {

        private String firstName;
    
        private String lastName;
    
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login implements Operrands {

        private String email;
        
        private String password;
    }
}
