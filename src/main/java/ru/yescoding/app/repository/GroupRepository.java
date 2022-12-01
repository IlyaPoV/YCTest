package ru.yescoding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yescoding.app.model.entity.GroupEntity;
import ru.yescoding.app.model.entity.UserEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {
}
