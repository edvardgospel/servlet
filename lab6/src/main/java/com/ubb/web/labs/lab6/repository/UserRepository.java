package com.ubb.web.labs.lab6.repository;

import com.ubb.web.labs.lab6.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT t FROM UserEntity t WHERE t.userName = ?1 AND t.password = ?2")
    UserEntity findByUserNameAndPassword(String userName, String password);

    @Query("SELECT u FROM UserEntity u WHERE u.userName = ?1")
    UserEntity findByUserName(String userName);
}
