package ru.yescoding.app.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "themes")
public class ThemesEntity {

    @Id
    @GeneratedValue
    @Column(name = "theme_id", nullable = false)
    private UUID themeId;

    @Column(name = "subject_id")
    private UUID subjectId;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "theme_id", foreignKey = @ForeignKey(name = "fk_themes"))
    @OneToMany(targetEntity = QuestionsEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuestionsEntity> questions;

    public ThemesEntity(UUID themeId, UUID subjectId, String title) {
        this.themeId = themeId;
        this.subjectId = subjectId;
        this.title = title;
    }

    public ThemesEntity(){}

    public UUID getThemeId() {
        return themeId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public String getTitle() {
        return title;
    }

    public List<QuestionsEntity> getQuestions() {
        return questions;
    }
}
