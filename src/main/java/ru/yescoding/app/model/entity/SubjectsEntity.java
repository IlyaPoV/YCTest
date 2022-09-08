package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subjects")
public class SubjectsEntity {

    @Id
    @Column(name = "subject_id", nullable = false)
    @GeneratedValue
    private UUID subjectId;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "fk_subjects"))
    @OneToMany(targetEntity = ThemesEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ThemesEntity> themes;

    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "fk_subjects"))
    @OneToMany(targetEntity = GroupsEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GroupsEntity> groups;

    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "fk_subjects"))
    @OneToMany(targetEntity = TestsEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestsEntity> tests;

    void SubjectsEntity(UUID subjectId, String title) {
        this.subjectId = subjectId;
        this.title = title;
    }

    void SubjectsEntity(){}

    public UUID getSubjectId() {
        return subjectId;
    }

    public String getTitle() {
        return title;
    }

    public List<ThemesEntity> getThemes() {
        return themes;
    }

    public List<GroupsEntity> getGroups() {
        return groups;
    }

    public List<TestsEntity> getTests() {
        return tests;
    }
}
