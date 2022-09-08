package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tests")
public class TestsEntity {

    @Id
    @GeneratedValue
    @Column(name = "test_id", nullable = false)
    private UUID testId;

    @Column(name = "title")
    private String title;

    @Column(name = "subject_id")
    private UUID subjectId;

    @JoinColumn(name = "test_id", foreignKey = @ForeignKey(name = "fk_tests"))
    @OneToMany(targetEntity = TestHistoriesEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestHistoriesEntity> testHistories;

    @ManyToMany(targetEntity = QuestionsEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "test_i_questions",
            joinColumns = {@JoinColumn(name = "test_id", foreignKey = @ForeignKey(name = "fk_tests"))},
            inverseJoinColumns = {@JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "fk_questions"))}
    )
    List<QuestionsEntity> questions;

    public TestsEntity(UUID testId, String title, UUID subjectId) {
        this.testId = testId;
        this.title = title;
        this.subjectId = subjectId;
    }

    public TestsEntity(){}

    public UUID getTestId() {
        return testId;
    }

    public String getTitle() {
        return title;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public List<TestHistoriesEntity> getTestHistories() {
        return testHistories;
    }

    public List<QuestionsEntity> getQuestions() {
        return questions;
    }
}
