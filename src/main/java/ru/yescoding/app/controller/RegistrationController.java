package ru.yescoding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yescoding.app.convert.GroupDtoConverter;
import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.repository.UserRepository;
import ru.yescoding.app.service.SecurityService;

@Controller
public class RegistrationController {
    private final SecurityService securityService;

    public RegistrationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/register")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/register") //localhost:8080/register
    public String processRegistration(RegistrationForm form) {
        securityService.registerNewUser(form);
        return "redirect:/authentication";
    }
}

