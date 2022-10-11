package ru.yescoding.app.convert;

import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.model.entity.UsersEntity;

public interface DtoConverter {
    UsersEntity convertUser(RegistrationForm registrationForm);
}
