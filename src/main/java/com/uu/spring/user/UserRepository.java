package com.uu.spring.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmailOrUsername(String email, String username);

    Optional<User> findUserByUsername(String username);
}
