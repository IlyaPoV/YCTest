package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "test_histories")
public class TestHistoriesEntity {

    @Id
    @GeneratedValue
    @Column(name = "test_histories_id")
    private UUID testHistoriesId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "test_id")
    private UUID testId;

    @Column(name = "passing_date")
    private Date passingDate;

    @JoinColumn(name = "test_histories_id", foreignKey = @ForeignKey(name = "fk_test_histories"))
    @OneToMany(targetEntity = TestHistoryAnswersEntity.class)
    private List<TestHistoryAnswersEntity> testHistoryAnswers;

    public TestHistoriesEntity(UUID testHistoriesId, UUID userId, UUID testId, Date passingDate) {
        this.testHistoriesId = testHistoriesId;
        this.userId = userId;
        this.testId = testId;
        this.passingDate = passingDate;
    }

    public TestHistoriesEntity(){}

    public UUID getTestHistoriesId() {
        return testHistoriesId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getTestId() {
        return testId;
    }

    public Date getPassingDate() {
        return passingDate;
    }

    public List<TestHistoryAnswersEntity> getTestHistoryAnswers() {
        return testHistoryAnswers;
    }
}
