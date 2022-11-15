package com.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public void save(NewAppUser newAppUser) throws UsernameAlreadyExistsException {
        String passwordBcrypt = SecurityConfig
                .passwordEncoder
                .encode(newAppUser.password());

        AppUser appUser = new AppUser(
                UUID.randomUUID().toString(),
                newAppUser.username(),
                passwordBcrypt,
                AppUserRole.BASIC);

        if (findByUsername(appUser.username()) != null) {
            throw new UsernameAlreadyExistsException("name is taken!");
        }
        appUserRepository.save(appUser);
    }

}
