package ru.yescoding.app.service.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Component;
import ru.yescoding.app.exception.VerifyException;
import ru.yescoding.app.security.AuthenticationConfigConstants;
import ru.yescoding.app.security.model.JWTConfig;
import ru.yescoding.app.service.TokenService;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TokenServiceImpl implements TokenService {
    private final JWTConfig config;
    private final JwtParser parser;

    public TokenServiceImpl(JWTConfig config) {
        this.parser = Jwts.parser().setSigningKey(config.getSecretKey());
//                .setSigningKeyResolver(new SigningKeyResolverAdapter() {
//                    @Override
//                    public Key resolveSigningKey(JwsHeader header, Claims claims) {
//                        if (claims.getIssuer() == null || claims.getIssuer().isBlank()) {
//                            throw new SignatureException("JWT could not be processed correctly");
//                        }
//                        String key = config.getSecretKey();
//                        if (key == null) {
//                            throw new SignatureException("JWT could not be processed correctly");
//                        }
//                        SignatureAlgorithm alg = SignatureAlgorithm.forName(header.getAlgorithm());
//                        if (alg.isHmac()) {
//                            return new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), alg.getJcaName());
//                        } else {
//                            throw new UnsupportedJwtException("");
//                        }
//                    }
//
//                    @Override
//                    public Key resolveSigningKey(JwsHeader header, String plaintext) {
//                        throw new SignatureException("JWT could not be processed correctly");
//                    }
//
//                    @Override
//                    public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
//                        return TextCodec.BASE64.decode(secrets.get(header.getAlgorithm()));
//                    }
//                });
        this.config = config;
    }

    @Override
    public String verifyToken(String fullToken) {
        if (fullToken == null || fullToken.isBlank()) {
            throw new VerifyException();
        }
        if (!fullToken.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            throw new VerifyException();
        }

        String token = fullToken.substring(AuthenticationConfigConstants.TOKEN_PREFIX.length());
        Jwt<JwsHeader<?>, Claims> jwt;
        try {
            jwt = parser.parse(token);
        } catch (JwtException e) {
            throw new VerifyException();
        }

        boolean verifyResult = verifyToken(jwt);
        if (!verifyResult) {
            throw new VerifyException();
        }
        return jwt.getBody().getSubject();
    }

    private boolean verifyToken(Jwt<JwsHeader<?>, Claims> jwt) {
        if (!(jwt instanceof Jws)) {
            return false;
        }

        Claims claims = jwt.getBody();

        String audience = claims.getAudience();
        if (audience == null || !audience.equals(config.getAudience())) {
            return false;
        }

        String issuer = claims.getIssuer();
        if (issuer == null || !issuer.equals(config.getIssuer())) {
            return false;
        }

        String subject = claims.getSubject();
        if (subject == null) {
            return false;
        }

        Date expiration = claims.getExpiration();
        if (expiration == null || expiration.before(Date.from(Instant.now()))) {
            return false;
        }
        return true;
    }

    @Override
    public String generateAccessToken(String userId) {
        return generateToken(userId, 5);
    }

    @Override
    public String generateRefreshToken(String userId) {
        return generateToken(userId, 60 * 48);
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
