package ru.yescoding.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yescoding.app.model.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserId(String username);
}
