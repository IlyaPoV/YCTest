package ru.yescoding.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "test_history_answers")
public class TestHistoryAnswersEntity {

    @Column(name = "test_histories_id")
    private UUID testHistoriesId;

    @Column(name = "answer_id")
    private UUID answerId;

    @Column(name = "question_id")
    private UUID questionId;

    public TestHistoryAnswersEntity(UUID testHistoriesId, UUID answerId, UUID questionId) {
        this.testHistoriesId = testHistoriesId;
        this.answerId = answerId;
        this.questionId = questionId;
    }

    public TestHistoryAnswersEntity(){}

    public UUID getTestHistoriesId() {
        return testHistoriesId;
    }

    public UUID getAnswerId() {
        return answerId;
    }

    public UUID getQuestionId() {
        return questionId;
    }
}
