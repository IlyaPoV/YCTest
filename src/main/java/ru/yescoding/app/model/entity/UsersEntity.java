package ru.yescoding.app.model.entity;

import ru.yescoding.app.model.EnumTypes.Role;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "name")
    private String name;

    @Column(name = "sname")
    private String sName;

    @Column(name = "role")
    private Role role;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_users"))
    @OneToMany(targetEntity = TestHistoriesEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestHistoriesEntity> testHistories;

    @ManyToMany(targetEntity = GroupsEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_i_group",
            joinColumns = {@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_users"))},
            inverseJoinColumns = {@JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_groups"))}
    )
    List<GroupsEntity> groups;

    public UsersEntity(UUID userId, String name, String sName, Role role) {
        this.userId = userId;
        this.name = name;
        this.sName = sName;
        this.role = role;
    }

    public UsersEntity(){}

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getsName() {
        return sName;
    }

    public Role getRole() {
        return role;
    }

    public List<GroupsEntity> getGroups() {
        return groups;
    }
}
