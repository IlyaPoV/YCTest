package ru.yescoding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yescoding.app.model.entity.ThemeEntity;

import java.util.UUID;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, UUID> {
}
