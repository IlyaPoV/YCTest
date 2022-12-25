package ru.yescoding.app.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.yescoding.app.convert.SuccessDtoConverter;
import ru.yescoding.app.model.dto.SuccessDto;
import ru.yescoding.app.model.entity.TestEntity;
import ru.yescoding.app.model.entity.UserEntity;
import ru.yescoding.app.repository.TestHistoryRepository;
import ru.yescoding.app.repository.TestRepository;
import ru.yescoding.app.repository.UserRepository;
import ru.yescoding.app.service.SuccessService;

import java.util.List;

@Service
public class SuccessServiceImpl implements SuccessService {

    private final SuccessDtoConverter successDtoConverter;
    private final UserRepository userRepository;

    public SuccessServiceImpl(SuccessDtoConverter successDtoConverter, UserRepository userRepository){
        this.successDtoConverter = successDtoConverter;
        this.userRepository = userRepository;
    }

    @Override
    public List<SuccessDto> getSuccess(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUserId(userName);
        return successDtoConverter.convertSucc(user.getTestHistories());
    }
}
