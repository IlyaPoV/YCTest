package ru.yescoding.app.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.yescoding.app.security.AuthenticationConfigConstants;
import ru.yescoding.app.security.model.UserDetailsImpl;
import ru.yescoding.app.security.util.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Configuration
public class JWTRefreshFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtils jwtUtils;

    public JWTRefreshFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        super(authenticationManager);
        super.setFilterProcessesUrl("/refresh-token");
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //TODO сюда добавить обновление токена, как в видосике https://www.youtube.com/watch?v=VVn9OG9nfH0 1:59:26
        return super.attemptAuthentication(request, response);
    }
}
