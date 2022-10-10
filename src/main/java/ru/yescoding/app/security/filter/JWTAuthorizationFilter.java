package ru.yescoding.app.security.filter;

import io.jsonwebtoken.*;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.yescoding.app.security.AuthenticationConfigConstants;
import ru.yescoding.app.security.model.JWTConfig;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JwtParser parser;
    private final JWTConfig config;


    public JWTAuthorizationFilter(JWTConfig config) {
        this.parser = Jwts.parser()
                .setSigningKeyResolver(new SigningKeyResolver() {
                    @Override
                    public Key resolveSigningKey(JwsHeader header, Claims claims) {
                        if (claims.getIssuer() == null || claims.getIssuer().isBlank()) {
                            throw new SignatureException("JWT could not be processed correctly");
                        }
                        String key = config.getSecretKey();
                        if (key == null) {
                            throw new SignatureException("JWT could not be processed correctly");
                        }
                        SignatureAlgorithm alg = SignatureAlgorithm.forName(header.getAlgorithm());
                        if (alg.isHmac()) {
                            return new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), alg.getJcaName());
                        } else {
                            throw new UnsupportedJwtException("");
                        }
                    }

                    @Override
                    public Key resolveSigningKey(JwsHeader header, String plaintext) {
                        throw new SignatureException("JWT could not be processed correctly");
                    }
                });
        this.config = config;
    }

    @Override
    protected void doFilterInternal(//TODo need confirm after
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || header.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!header.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(AuthenticationConfigConstants.TOKEN_PREFIX.length());
        Jwt<JwsHeader<?>, Claims> jwt;
        try {
            jwt = this.parser.parse(token);
        } catch (JwtException e) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!(jwt instanceof Jws)) {
            filterChain.doFilter(request, response);
            return;
        }

        Object aud = jwt.getBody().get("aud");
        if (aud instanceof String && !config.getAudience().equals(aud)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (aud instanceof List && !((List<?>) aud).contains(config.getAudience())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!(aud instanceof String) && !(aud instanceof List)) {
            filterChain.doFilter(request, response);
            return;
        }

        String subject = jwt.getBody().getSubject();
        if (subject == null) {
            subject = jwt.getBody().getIssuer();
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                subject, null, List.of()//todo добавление приоритетов
        );
        authentication.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
