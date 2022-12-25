package ru.yescoding.app.convert.impl;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Component;
import ru.yescoding.app.convert.SuccessDtoConverter;
import ru.yescoding.app.model.dto.SuccessDto;
import ru.yescoding.app.model.entity.TestHistoryEntity;
import ru.yescoding.app.repository.SubjectRepository;
import ru.yescoding.app.repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SuccessDtoConverterImpl implements SuccessDtoConverter {

    private final TestRepository testRepository;
    private SubjectRepository subjectRepository;

    public SuccessDtoConverterImpl(TestRepository testRepository, SubjectRepository subjectRepository){
        this.testRepository = testRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SuccessDto> convertSucc(List<TestHistoryEntity> testHistoryEntities){
        return testHistoryEntities
                .stream()
                .map(
                t -> new SuccessDto(
                        testRepository.findById(t.getTestId()).get().getTitle(),
                        "1/2", //TODO
                        10, //TODO
                        subjectRepository.findById(testRepository.findById(t.getTestId()).get().getSubjectId()).get().getTitle(),
                        t.getPassingDate()
                )
        )
                .collect(Collectors.toList());
    }
}
