package ru.yescoding.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.yescoding.app.security.filter.JWTAuthenticationFilter;
import ru.yescoding.app.security.filter.JWTAuthorizationFilter;

@Configuration
public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(
            JWTAuthenticationFilter jwtAuthenticationFilter,
            JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] staticResources = {
                "/style/**",
                "/images/**",
                "/scripts/**"};
        return http
//                .sessionManagement(
//                        sessionManagement -> sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .antMatchers("/register", "/authentication").permitAll()
                                .antMatchers(staticResources).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/authentication")
                .defaultSuccessUrl("/groups")
                .and()
                .logout()
                .logoutSuccessUrl("/authentication")
                .and()
                .addFilter(this.jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
