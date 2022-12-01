package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tests")
public class TestEntity {

    @Id
    @GeneratedValue
    @Column(name = "test_id", nullable = false)
    private UUID testId;

    @Column(name = "title")
    private String title;

    @Column(name = "subject_id")
    private UUID subjectId;

    @JoinColumn(name = "test_id", foreignKey = @ForeignKey(name = "fk_tests"))
    @OneToMany(targetEntity = TestHistoryEntity.class)
    private List<TestHistoryEntity> testHistories;

    @ManyToMany(targetEntity = QuestionEntity.class)
    @JoinTable(
            name = "test_i_questions",
            joinColumns = {@JoinColumn(name = "test_id", foreignKey = @ForeignKey(name = "fk_tests"))},
            inverseJoinColumns = {@JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "fk_questions"))}
    )
    private List<QuestionEntity> questions;

    public TestEntity(UUID testId, String title, UUID subjectId) {
        this.testId = testId;
        this.title = title;
        this.subjectId = subjectId;
    }

    public TestEntity(){}

    public UUID getTestId() {
        return testId;
    }

    public String getTitle() {
        return title;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public List<TestHistoryEntity> getTestHistories() {
        return testHistories;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }
}
