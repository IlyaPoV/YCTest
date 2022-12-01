package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "themes")
public class ThemeEntity {

    @Id
    @GeneratedValue
    @Column(name = "theme_id", nullable = false)
    private UUID themeId;

    @Column(name = "subject_id")
    private UUID subjectId;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "theme_id", foreignKey = @ForeignKey(name = "fk_themes"))
    @OneToMany(targetEntity = QuestionEntity.class)
    private List<QuestionEntity> questions;

    public ThemeEntity(UUID themeId, UUID subjectId, String title) {
        this.themeId = themeId;
        this.subjectId = subjectId;
        this.title = title;
    }

    public ThemeEntity(){}

    public UUID getThemeId() {
        return themeId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public String getTitle() {
        return title;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }
}
