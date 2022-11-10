package com.webapp.endavacourseproject.repository;

import com.webapp.endavacourseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select id from users where first_name = ?1 and last_name = ?2")
    Long getUserID(String n1, String n2);

    @Query(value = "select * from users", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "select * from users limit ?1", nativeQuery = true)
    List<User> getAllUsers(Long l);
}
