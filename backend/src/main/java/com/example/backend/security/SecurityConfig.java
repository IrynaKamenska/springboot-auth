package com.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AppUserService appUserService;
    public final static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder encoder() {
        return passwordEncoder;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/me") .permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/login",
                        "/api/logout",
                        "/api/role").authenticated()
                .antMatchers(HttpMethod.POST,
                        "/api").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and().build();
    }


    @Bean
    UserDetailsManager userDetailsManager(){
        return new UserDetailsManager() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser userByName = appUserService.findByUsername(username);
                if(userByName == null) {
                    throw new UsernameNotFoundException("Username " + username + " not found");
                }

                return User.builder()
                        .username(username)
                        .password(userByName.passwordBcrypt())
                        .roles(userByName.role().toString())
                        .build();
            }
            @Override
            public void createUser(UserDetails user) {

            }

            @Override
            public void updateUser(UserDetails user) {

            }

            @Override
            public void deleteUser(String username) {

            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {

            }

            @Override
            public boolean userExists(String username) {
                return false;
            }

        };
    }

}
