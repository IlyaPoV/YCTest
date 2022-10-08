package ru.yescoding.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class YesCodingTestPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(YesCodingTestPlatformApplication.class, args);
    }

}
