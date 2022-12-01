package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "theme_id")
    private UUID themeId;

    @Column(name = "points")
    private int points;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "q_type")
    private String q_type;

    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "fk_questions"))
    @OneToMany(targetEntity = AnswerEntity.class)
    private List<AnswerEntity> answers;

    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "fk_questions"))
    @OneToMany(targetEntity = TestHistoryAnswerEntity.class)
    private List<TestHistoryAnswerEntity> testHistoriesAnswer;

    @ManyToMany(mappedBy = "questions")
    private List<TestEntity> tests;

    public QuestionEntity(UUID questionId, UUID themeId, int points, String questionText, String q_type) {
        this.questionId = questionId;
        this.themeId = themeId;
        this.points = points;
        this.questionText = questionText;
        this.q_type = q_type;
    }

    public QuestionEntity(){}

    public UUID getQuestionId() {
        return questionId;
    }

    public UUID getThemeId() {
        return themeId;
    }

    public int getPoints() {
        return points;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getType() {
        return q_type;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public List<TestHistoryAnswerEntity> getTestHistoriesAnswer() {
        return testHistoriesAnswer;
    }

    public List<TestEntity> getTests() {
        return tests;
    }
}
