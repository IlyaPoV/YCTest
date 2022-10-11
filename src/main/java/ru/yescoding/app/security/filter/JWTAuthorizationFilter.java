package ru.yescoding.app.security.filter;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.yescoding.app.exception.VerifyException;
import ru.yescoding.app.security.AuthenticationConfigConstants;
import ru.yescoding.app.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    public JWTAuthorizationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (request.getCookies() == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Cookie accessCookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(AuthenticationConfigConstants.AUTH_ACCESS_TOKEN_HEADER))
                .findFirst().orElse(null);
        Cookie refreshCookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(AuthenticationConfigConstants.AUTH_REFRESH_TOKEN_HEADER))
                .findFirst().orElse(null);

        if (accessCookie == null || refreshCookie == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String subject;
        try {
            subject = tokenService.verifyToken(accessCookie.getValue());
        } catch (VerifyException ex1) {
            try {
                subject = tokenService.verifyToken(refreshCookie.getValue());
                tryHandleRefreshToken(subject, response);
            } catch (VerifyException ex2) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                subject, null, List.of()//todo добавление приоритетов
        );
        authentication.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private void tryHandleRefreshToken(String subject, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Cookie cookie = new Cookie(
                AuthenticationConfigConstants.AUTH_REFRESH_TOKEN_HEADER,
                AuthenticationConfigConstants.TOKEN_PREFIX + tokenService.generateRefreshToken(subject)
        );
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        cookie = new Cookie(
                AuthenticationConfigConstants.AUTH_ACCESS_TOKEN_HEADER,
                AuthenticationConfigConstants.TOKEN_PREFIX + tokenService.generateAccessToken(subject)
        );
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
