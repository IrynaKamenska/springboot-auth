package com.example.backend.security;

public record AppUser(String id, String username, String passwordBcrypt, AppUserRole role) {
}
