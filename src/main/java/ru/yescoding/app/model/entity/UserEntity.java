package ru.yescoding.app.model.entity;

import ru.yescoding.app.model.entity.enumTypes.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_users"))
    @OneToMany(targetEntity = TestHistoryEntity.class)
    private List<TestHistoryEntity> testHistories;

    @ManyToMany(targetEntity = GroupEntity.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_i_group",
            joinColumns = {@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_users"))},
            inverseJoinColumns = {@JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_groups"))}
    )
    private List<GroupEntity> groups;

    public UserEntity(String userId, String password, String firstName, String secondName, Role role) {
        this.userId = userId;
        this.password = password;
        this.name = firstName;
        this.secondName = secondName;
        this.role = role;
    }

    public UserEntity(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<TestHistoryEntity> getTestHistories() {
        return testHistories;
    }

    public void setTestHistories(List<TestHistoryEntity> testHistories) {
        this.testHistories = testHistories;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }
}
