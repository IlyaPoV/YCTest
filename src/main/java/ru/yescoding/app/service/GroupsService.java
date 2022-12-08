package ru.yescoding.app.service;

import ru.yescoding.app.model.dto.GroupDto;

import java.util.List;

public interface GroupsService {
    List<GroupDto> getGroups();
}
