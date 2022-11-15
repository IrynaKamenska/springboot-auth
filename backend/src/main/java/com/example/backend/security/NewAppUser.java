package com.example.backend.security;

import lombok.NonNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record NewAppUser(String username,
         @NonNull
         @NotEmpty(message = "Password must not be empty")
         @Pattern(regexp = "^(?=[^A-Z]*+[A-Z])(?=[^a-z]*+[a-z])(?=\\D*+\\d)(?=[^#?!@$ %^&*-]*+[#?!@$ %^&*-]).{6,30}$", message = "Password must have minimum eight characters, at least one letter and one number")
         String password, AppUserRole role) {
}
