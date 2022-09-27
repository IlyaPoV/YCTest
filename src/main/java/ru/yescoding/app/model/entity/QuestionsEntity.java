package ru.yescoding.app.model.entity;

import ru.yescoding.app.model.entity.enumTypes.QuestionType;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "questions")
public class QuestionsEntity {

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
    @OneToMany(targetEntity = AnswersEntity.class)
    private List<AnswersEntity> answers;

    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "fk_questions"))
    @OneToMany(targetEntity = TestHistoryAnswersEntity.class)
    private List<TestHistoryAnswersEntity> testHistoriesAnswer;

    @ManyToMany(mappedBy = "questions")
    private List<TestsEntity> tests;

    public QuestionsEntity(UUID questionId, UUID themeId, int points, String questionText, String q_type) {
        this.questionId = questionId;
        this.themeId = themeId;
        this.points = points;
        this.questionText = questionText;
        this.q_type = q_type;
    }

    public QuestionsEntity(){}

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

    public List<AnswersEntity> getAnswers() {
        return answers;
    }

    public List<TestHistoryAnswersEntity> getTestHistoriesAnswer() {
        return testHistoriesAnswer;
    }

    public List<TestsEntity> getTests() {
        return tests;
    }
}
