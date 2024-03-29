package ru.yescoding.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
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
                .cors()
                .configurationSource(
                        cors -> new CorsConfiguration().applyPermitDefaultValues()
                )
                .and()
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .antMatchers("/register", "/authentication", "/refresh-token").permitAll()
                                .antMatchers(staticResources).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/authentication")
                .and()
                .logout(
                        logoutConfig -> logoutConfig.logoutSuccessUrl("/authentication")
                                .deleteCookies(
                                        AuthenticationConfigConstants.AUTH_ACCESS_TOKEN_HEADER,
                                        AuthenticationConfigConstants.AUTH_REFRESH_TOKEN_HEADER
                                )
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                )
                .exceptionHandling()
                .authenticationEntryPoint((rq, rs, e) -> {
                    if (HttpMethod.GET.matches(rq.getMethod())) {
                        rs.sendRedirect("/authentication");
                    }
                    rs.setStatus(HttpStatus.UNAUTHORIZED.value());
                })
                .and()
                .addFilter(this.jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf()
                .ignoringAntMatchers("/logout")
                .and()
                .build();
    }
}
