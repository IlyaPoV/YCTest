package ru.yescoding.app.security.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app-config.auth")
@ConstructorBinding
//@Component
public class JWTConfig {
    private final String audience;
    private final String issuer;
    private final String secretKey;
    private final String algorithm;

    public JWTConfig(String audience, String issuer, String secretKey, String algorithm) {
        this.audience = audience;
        this.issuer = issuer;
        this.secretKey = secretKey;
        this.algorithm = algorithm;
    }

//    public JWTConfig() {
//        this.audience = "yc-test-platform-user";
//        this.issuer = "yc-test-platform";
//        this.secretKey = "a-very-secret-key";
//        this.algorithm = "HS256";
//    }

    public String getAudience() {
        return audience;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
