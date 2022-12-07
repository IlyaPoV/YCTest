package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue
    @Column(name = "group_id", nullable = false)
    private UUID groupId;

    @Column(name = "grade")
    private int grade;

    @JoinColumn(name = "subject_id")
    @ManyToOne
    private SubjectEntity subject;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "groups")
    private List<UserEntity> users;

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subjectId) {
        this.subject = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
