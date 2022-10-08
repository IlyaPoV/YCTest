package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yescoding.app.convert.DtoConverter;
import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserRepository userRepository;
    private final DtoConverter converter;

    public RegistrationController(
            UserRepository userRepository,
            DtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepository.save(
                converter.convertUser(form)
        );
        return "redirect:/authentication";
    }
}

