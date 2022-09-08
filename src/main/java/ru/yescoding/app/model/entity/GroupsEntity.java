package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "groups")
public class GroupsEntity {

    @Id
    @GeneratedValue
    @Column(name = "group_id", nullable = false)
    private UUID groupId;

    @Column(name = "grade")
    private int grade;

    @Column(name = "subject_id")
    private UUID subjectId;

    @ManyToMany(mappedBy = "groups")
    List<UsersEntity> users;

    public GroupsEntity(UUID groupId, int grade, UUID subjectId) {
        this.groupId = groupId;
        this.grade = grade;
        this.subjectId = subjectId;
    }

    public GroupsEntity(){}

    public UUID getGroupId() {
        return groupId;
    }

    public int getGrade() {
        return grade;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public List<UsersEntity> getUsers() {
        return users;
    }
}
