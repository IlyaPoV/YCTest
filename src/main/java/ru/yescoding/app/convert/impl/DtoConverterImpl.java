package ru.yescoding.app.convert.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.yescoding.app.convert.DtoConverter;
import ru.yescoding.app.model.dto.GroupDto;
import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.model.entity.GroupEntity;
import ru.yescoding.app.model.entity.UserEntity;
import ru.yescoding.app.model.entity.enumTypes.Role;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverterImpl implements DtoConverter {
    private final PasswordEncoder passwordEncoder;

    public DtoConverterImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity convertUser(RegistrationForm registrationForm) {
        return new UserEntity(
                registrationForm.getUsername(),
                passwordEncoder.encode(
                        registrationForm.getPassword()
                ),
                registrationForm.getFirstName(),
                registrationForm.getSecondName(),
                Role.STUDENT
        );
    }

    @Override
    public List<GroupDto> convertGroups(UserEntity user, List<GroupEntity> groups) {
        return groups
                .stream()
                .map(
                        g -> new GroupDto(
                                g.getTitle(),
                                g.getSubject().getTitle(),
                                0, //TODO
                                1, //TODO
                                user.getName()
                        )
                )
                .collect(Collectors.toList());
    }
}
