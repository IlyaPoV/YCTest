package ru.yescoding.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.yescoding.app.model.entity.UsersEntity;
import ru.yescoding.app.repository.UserRepository;
import ru.yescoding.app.security.model.UserDetailsImpl;

@Configuration
public class SecurityBeanConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            UsersEntity user = userRepo.findByUserId(username);
            if (user == null) {
                throw new UsernameNotFoundException("User ‘" + username + "’ not found");
            }
            return UserDetailsImpl.build(user);
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance(); //TODO remove after tests
    }
}
