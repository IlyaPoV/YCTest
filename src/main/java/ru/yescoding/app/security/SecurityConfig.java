package ru.yescoding.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import ru.yescoding.app.security.filter.JWTAuthenticationFilter;
import ru.yescoding.app.security.filter.JWTAuthorizationFilter;
import ru.yescoding.app.security.filter.JWTRefreshFilter;

@Configuration
public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final JWTRefreshFilter jwtRefreshFilter;

    public SecurityConfig(
            JWTAuthenticationFilter jwtAuthenticationFilter,
            JWTAuthorizationFilter jwtAuthorizationFilter,
            JWTRefreshFilter jwtRefreshFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtRefreshFilter = jwtRefreshFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] staticResources = {
                "/style/**",
                "/images/**",
                "/scripts/**"};
        return http
                .cors()
                .configurationSource(
                        cors -> new CorsConfiguration().applyPermitDefaultValues()
                )
                .and()
//                .sessionManagement(
//                        sessionManagement -> sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .antMatchers("/register", "/authentication", "/refresh-token").permitAll()
                                .antMatchers(staticResources).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/authentication")
                .and()
                .logout()
                .logoutSuccessUrl("/authentication")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((rq, rs, e) -> {
                    rs.setStatus(HttpStatus.UNAUTHORIZED.value());
                    rs.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer");
                })
                .and()
                .addFilter(this.jwtAuthenticationFilter)
//                .addFilter(this.jwtRefreshFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
