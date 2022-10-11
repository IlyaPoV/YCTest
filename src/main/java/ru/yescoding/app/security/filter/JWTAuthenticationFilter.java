package ru.yescoding.app.security.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.yescoding.app.security.AuthenticationConfigConstants;
import ru.yescoding.app.security.model.UserDetailsImpl;
import ru.yescoding.app.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final TokenService tokenService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
        super(authenticationManager);
        super.setFilterProcessesUrl("/authenticate");
        this.tokenService = tokenService;
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException {
        UserDetailsImpl user = (UserDetailsImpl) authResult.getPrincipal(); //TODO resolve authorities
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Cookie cookie = new Cookie(
                AuthenticationConfigConstants.AUTH_REFRESH_TOKEN_HEADER,
                AuthenticationConfigConstants.TOKEN_PREFIX + tokenService.generateRefreshToken(user.getUsername())
        );
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        cookie = new Cookie(
                AuthenticationConfigConstants.AUTH_ACCESS_TOKEN_HEADER,
                AuthenticationConfigConstants.TOKEN_PREFIX + tokenService.generateAccessToken(user.getUsername())
        );
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        response.sendRedirect(AuthenticationConfigConstants.DEFAULT_REDIRECT_URI);
    }
}
