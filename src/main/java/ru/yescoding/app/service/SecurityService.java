package ru.yescoding.app.service;

import ru.yescoding.app.model.dto.RegistrationForm;

public interface SecurityService {
    void registerNewUser(RegistrationForm registrationForm);
}
