package ru.yescoding.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.yescoding.app.security.model.JWTConfig;

@SpringBootApplication
@EnableWebSecurity
@EnableWebMvc
@EnableConfigurationProperties(value = {JWTConfig.class})
public class YesCodingTestPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(YesCodingTestPlatformApplication.class, args);
    }

}
