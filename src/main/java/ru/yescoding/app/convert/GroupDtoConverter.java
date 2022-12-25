package ru.yescoding.app.convert;

import ru.yescoding.app.model.dto.GroupDto;
import ru.yescoding.app.model.dto.RegistrationForm;
import ru.yescoding.app.model.entity.GroupEntity;
import ru.yescoding.app.model.entity.UserEntity;

import java.util.List;

public interface GroupDtoConverter {
    UserEntity convertUser(RegistrationForm registrationForm);
    List<GroupDto> convertGroups(UserEntity user, List<GroupEntity> groups);
}
