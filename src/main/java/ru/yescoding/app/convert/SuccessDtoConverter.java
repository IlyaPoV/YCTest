package ru.yescoding.app.convert;

import ru.yescoding.app.model.dto.SuccessDto;
import ru.yescoding.app.model.entity.GroupEntity;
import ru.yescoding.app.model.entity.TestHistoryEntity;

import java.util.List;

public interface SuccessDtoConverter {
    public List<SuccessDto> convertSucc(List<TestHistoryEntity> testHistoryEntities);
}
