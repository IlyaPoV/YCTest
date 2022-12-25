package ru.yescoding.app.service.impl;

import org.springframework.stereotype.Service;
import ru.yescoding.app.convert.GroupDtoConverter;
import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.repository.UserRepository;
import ru.yescoding.app.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final GroupDtoConverter converter;
    private final UserRepository userRepository;

    public SecurityServiceImpl(GroupDtoConverter converter, UserRepository userRepository) {
        this.converter = converter;
        this.userRepository = userRepository;
    }

    @Override
    public void registerNewUser(RegistrationForm registrationForm) {
        userRepository.save(
                converter.convertUser(registrationForm)
        );
    }
}
