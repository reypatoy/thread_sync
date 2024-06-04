package com.threadsync.project.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    String query =  """
                    from User u where  
                    (:firstName is null or u.firstName = :firstName) 
                    and 
                    (:lastName is null or u.lastName = :lastName)
                    and 
                    (:email is null or u.email = :email)
                    """;
    @Query("select u " + query)
    List<User> search(@Param("lastName") String lastName,
                        @Param("firstName") String firstName,
                        @Param("email") String email,
                        Pageable pageable
                    );

    @Query("select count(u)" + query)
    long total(@Param("lastName") String lastName,
                        @Param("firstName") String firstName,
                        @Param("email") String email
                    );

    @Query("select u from User u where email = :email")
    Optional<User> getByEmail(@Param("email") String email);
}
