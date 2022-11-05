package com.webapp.endavacourseproject.repository;

import com.webapp.endavacourseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    @Query(value = "select * from users", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "select * from users limit l offset 1", nativeQuery = true)
    List<User> getAllUsers(Long l);
}
