package ru.yescoding.app.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.yescoding.app.convert.GroupDtoConverter;
import ru.yescoding.app.model.dto.GroupDto;
import ru.yescoding.app.model.entity.UserEntity;
import ru.yescoding.app.repository.GroupRepository;
import ru.yescoding.app.repository.UserRepository;
import ru.yescoding.app.service.GroupsService;

import java.util.List;

@Service
public class GroupsServiceImpl implements GroupsService {

    private final UserRepository userRepository;
    private final GroupDtoConverter converter;

    public GroupsServiceImpl(UserRepository userRepository, GroupDtoConverter converter){
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public List<GroupDto> getGroups(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUserId(userName);
        return converter.convertGroups(
                user,
                user.getGroups()
        );
    }
}
