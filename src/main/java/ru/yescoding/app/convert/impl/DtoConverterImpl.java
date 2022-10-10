package ru.yescoding.app.convert.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.yescoding.app.convert.DtoConverter;
import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.model.entity.UsersEntity;
import ru.yescoding.app.model.entity.enumTypes.Role;

@Component
public class DtoConverterImpl implements DtoConverter {
    private final PasswordEncoder passwordEncoder;

    public DtoConverterImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsersEntity convertUser(RegistrationForm registrationForm) {
        return new UsersEntity(
                registrationForm.getUsername(),
                passwordEncoder.encode(
                        registrationForm.getPassword()
                ),
                registrationForm.getFirstName(),
                registrationForm.getSecondName(),
                Role.STUDENT
        );
    }
}