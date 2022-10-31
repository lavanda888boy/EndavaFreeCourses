package com.webapp.endavacourseproject.repository;

import com.webapp.endavacourseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
