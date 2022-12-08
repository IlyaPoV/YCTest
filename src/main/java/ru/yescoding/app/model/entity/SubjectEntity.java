package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subjects")
public class SubjectEntity {

    @Id
    @Column(name = "subject_id", nullable = false)
    @GeneratedValue
    private UUID subjectId;

    @Column(name = "title")
    private String title;

    @Column(name = "grade")
    private int grade;

    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "fk_subjects"))
    @OneToMany(targetEntity = ThemeEntity.class)
    private List<ThemeEntity> themes;

    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "fk_subjects"))
    @OneToMany(targetEntity = GroupEntity.class)
    private List<GroupEntity> groups;

    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "fk_subjects"))
    @OneToMany(targetEntity = TestEntity.class)
    private List<TestEntity> tests;

    public void SubjectEntity(UUID subjectId, String title, int grade) {
        this.subjectId = subjectId;
        this.title = title;
        this.grade = grade;
    }

    public void SubjectEntity(){}

    public UUID getSubjectId() {
        return subjectId;
    }

    public String getTitle() {
        return title;
    }

    public int getGrade() {
        return grade;
    }

    public List<ThemeEntity> getThemes() {
        return themes;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public List<TestEntity> getTests() {
        return tests;
    }

}
