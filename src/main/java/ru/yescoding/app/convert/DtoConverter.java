package ru.yescoding.app.convert;

import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.model.entity.UserEntity;

public interface DtoConverter {
    UserEntity convertUser(RegistrationForm registrationForm);
}
