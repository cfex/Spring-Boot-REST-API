package com.restapi.bookstore.repository;

import com.restapi.bookstore.model.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    Boolean existsByUserName(@NotNull String userName);

    Boolean existsByEmail(@NotNull String email);
}
