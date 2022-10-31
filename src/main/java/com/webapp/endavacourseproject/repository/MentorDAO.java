package com.webapp.endavacourseproject.repository;

import com.webapp.endavacourseproject.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorDAO extends JpaRepository<Mentor, Long> {
}
