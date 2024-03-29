package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "answers")
public class AnswerEntity {

    @Id
    @GeneratedValue
    @Column(name = "answer_id", nullable = false)
    private UUID answerId;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "is_right")
    private boolean isRight;

    @Column(name = "answer_text")
    private String answerText;

    @JoinColumn(name = "answer_id", foreignKey = @ForeignKey(name = "fk_answers"))
    @OneToMany(targetEntity = TestHistoryAnswerEntity.class)
    private List<TestHistoryAnswerEntity> testHistoryAnswers;

    public AnswerEntity(UUID answerId, UUID questionId, boolean isRight, String answerText) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.isRight = isRight;
        this.answerText = answerText;
    }

    public AnswerEntity(){}

    public UUID getAnswerId() {
        return answerId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public boolean isRight() {
        return isRight;
    }

    public String getAnswerText() {
        return answerText;
    }

    public List<TestHistoryAnswerEntity> getTestHistoryAnswers() {
        return testHistoryAnswers;
    }
}
