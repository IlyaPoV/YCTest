package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "test_history_answers")
public class TestHistoryAnswerEntity {
    @EmbeddedId
    @Column(name = "test_histories_id")
    private CompositeTestHistoryAnswersId compositeTestHistoryAnswersId;

    @Column(name = "answer_id", insertable = false, updatable = false)
    private UUID answerId;

    @Column(name = "question_id", insertable = false, updatable = false)
    private UUID questionId;

    @Column(name = "test_histories_id", insertable = false, updatable = false)
    private UUID testHistoriesId;

    @ManyToOne
    @JoinColumn(name = "test_histories_id", insertable = false, updatable = false)
    private TestHistoryEntity testHistories;

    @ManyToOne
    @JoinColumn(name = "answer_id", insertable = false, updatable = false)
    private AnswerEntity answers;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private QuestionEntity questions;

    @Embeddable
    private static class CompositeTestHistoryAnswersId implements Serializable {
        @Column(name = "test_histories_id")
        private UUID testHistoriesId;

        @Column(name = "answer_id")
        private UUID answerId;

        @Column(name = "question_id")
        private UUID questionId;

        public UUID getTestHistoriesId() {
            return testHistoriesId;
        }

        public void setTestHistoriesId(UUID testHistoriesId) {
            this.testHistoriesId = testHistoriesId;
        }

        public UUID getAnswerId() {
            return answerId;
        }

        public void setAnswerId(UUID answerId) {
            this.answerId = answerId;
        }

        public UUID getQuestionId() {
            return questionId;
        }

        public void setQuestionId(UUID questionId) {
            this.questionId = questionId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeTestHistoryAnswersId that = (CompositeTestHistoryAnswersId) o;
            return Objects.equals(testHistoriesId, that.testHistoriesId)
                    && Objects.equals(answerId, that.answerId)
                    && Objects.equals(questionId, that.questionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(testHistoriesId, answerId, questionId);
        }
    }

    public TestHistoryAnswerEntity(){}

    public CompositeTestHistoryAnswersId getTestHistoriesId() {
        return compositeTestHistoryAnswersId;
    }

    public void setTestHistoriesId(CompositeTestHistoryAnswersId compositeTestHistoryAnswersId) {
        this.compositeTestHistoryAnswersId = compositeTestHistoryAnswersId;
    }

    public UUID getAnswerId() {
        return answerId;
    }

    public UUID getQuestionId() {
        return questionId;
    }
}
