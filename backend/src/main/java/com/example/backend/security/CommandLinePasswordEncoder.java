package com.example.backend.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommandLinePasswordEncoder {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("Password25#"));
// user
//        $2a$10$dkizBpesMVSZWPuYi6eNVuLkIX7eFn7g24qXp6GW0FsJhi2U/eTEG
    }
}
