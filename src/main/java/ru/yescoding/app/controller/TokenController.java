package ru.yescoding.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yescoding.app.exception.VerifyException;
import ru.yescoding.app.security.AuthenticationConfigConstants;
import ru.yescoding.app.service.TokenService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        Cookie authorizationCookie = Arrays.stream(request.getCookies())
                .filter(f -> f.getName().equalsIgnoreCase(AuthenticationConfigConstants.AUTH_REFRESH_TOKEN_HEADER))
                .findFirst()
                .orElse(null);
        if (authorizationCookie == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        String subject;
        try {
            subject = tokenService.verifyToken(authorizationCookie.getValue());
        } catch (VerifyException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
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

//
//        new ObjectMapper().writeValue(
//                response.getOutputStream(),
//                Map.of(
//                        AuthenticationConfigConstants.AUTH_ACCESS_TOKEN_HEADER,
//                        AuthenticationConfigConstants.TOKEN_PREFIX + tokenService.generateAccessToken(subject)
//                )
//        );
    }
}
