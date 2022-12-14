package com.example.backend.security;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
     AppUser findByUsername(String username);
}
