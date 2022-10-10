package ru.yescoding.app.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import ru.yescoding.app.security.model.JWTConfig;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtils {
    private final JWTConfig config;

    public JwtUtils(JWTConfig config) {
        this.config = config;
    }

    public String generateAccessToken(String userId) {
        return generateToken(userId, 5);
    }

    public String generateRefreshToken(String userId) {
        return generateToken(userId, 60 * 24);
    }

    private String generateToken(String userId, int duration) {
        Instant now = Instant.now();
        Instant exp = now.plus(duration, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setIssuer(config.getIssuer())
                .setAudience(config.getAudience())
                .setSubject(userId)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(
                        SignatureAlgorithm.HS256,
                        config.getSecretKey()
                )
                .compact();
    }
}
