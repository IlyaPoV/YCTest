package ru.yescoding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yescoding.app.model.entity.UsersEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
    UsersEntity findByUserId(String username);
}
