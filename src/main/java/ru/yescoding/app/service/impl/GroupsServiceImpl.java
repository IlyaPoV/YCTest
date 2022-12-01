package ru.yescoding.app.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.yescoding.app.model.dto.GroupDto;
import ru.yescoding.app.model.entity.GroupEntity;
import ru.yescoding.app.model.entity.UserEntity;
import ru.yescoding.app.repository.GroupRepository;
import ru.yescoding.app.repository.UserRepository;
import ru.yescoding.app.service.GroupsService;

import java.util.List;

public class GroupsServiceImpl implements GroupsService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public GroupsServiceImpl(UserRepository userRepository, GroupRepository groupRepository){

        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public GroupDto getGroups(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity byUserId = userRepository.findByUserId(currentPrincipalName);
        List<GroupEntity> groups = byUserId.getGroups();
//        groups
//                .stream()
//                .map(GroupsEntity::getUsers)
//                .filter(
//                       u -> u
//                                .stream()
//                                .filter(usersEntity -> usersEntity.getRole().equals(Role.TEACHER))
//                )
//                .;
        return null;
    }
}
