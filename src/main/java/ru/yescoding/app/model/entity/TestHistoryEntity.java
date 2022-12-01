package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "test_histories")
public class TestHistoryEntity {

    @Id
    @GeneratedValue
    @Column(name = "test_histories_id")
    private UUID testHistoriesId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "test_id")
    private UUID testId;

    @Column(name = "passing_date")
    private LocalDate passingDate;

    @JoinColumn(name = "test_histories_id", foreignKey = @ForeignKey(name = "fk_test_histories"))
    @OneToMany(targetEntity = TestHistoryAnswerEntity.class)
    private List<TestHistoryAnswerEntity> testHistoryAnswers;

    public TestHistoryEntity(UUID testHistoriesId, UUID userId, UUID testId, LocalDate passingDate) {
        this.testHistoriesId = testHistoriesId;
        this.userId = userId;
        this.testId = testId;
        this.passingDate = passingDate;
    }

    public TestHistoryEntity(){}

    public UUID getTestHistoriesId() {
        return testHistoriesId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getTestId() {
        return testId;
    }

    public LocalDate getPassingDate() {
        return passingDate;
    }

    public List<TestHistoryAnswerEntity> getTestHistoryAnswers() {
        return testHistoryAnswers;
    }
}
