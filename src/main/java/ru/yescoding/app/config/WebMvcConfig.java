package ru.yescoding.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/groups");
//        registry.addViewController("/authentication").setViewName("auth-page");
//        registry.addViewController("/exercises").setViewName("exercise-page");
//        registry.addViewController("/groups").setViewName("group-page");
//        registry.addViewController("/group-exercise").setViewName("groups-exercise-page");
//        registry.addViewController("/register").setViewName("registration");
//        registry.addViewController("/successes").setViewName("success-page");
    }
}