package ru.yescoding.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.yescoding.app.model.entity.UserEntity;
import ru.yescoding.app.repository.UserRepository;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance(); //TODO remove after tests
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            UserEntity user = userRepo.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User ‘" + username + "’ not found");
            }
            return user;
        };
    }

    @Bean
    public AuthenticationManager authManager(
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] staticResources = {
                "/style/**",
                "/images/**",
                "/scripts/**"};
        return http
                .authorizeRequests()
                .antMatchers("/register", "/authentication").permitAll()
                .antMatchers(staticResources).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/authentication")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/groups")
                .and()
                .logout()
                .logoutSuccessUrl("/authentication")
                .and()
                .build();
    }
// http.authorizeRequests()
//         .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/resource/**").permitAll()
//                .antMatchers("/roditelj/**").hasAuthority("roditelj")
//                .antMatchers("/admin/**").hasAuthority("admin")
//                .antMatchers("/ucitelj/**").hasAuthority("ucitelj")
//                .antMatchers(staticResources).permitAll()
//                .anyRequest()
//                .authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error=true")
//                .successHandler(authenticationSuccessHandler)
//                .usernameParameter("username")
//                .passwordParameter("password").and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
//                .exceptionHandling().accessDeniedPage("/access-denied");


}
