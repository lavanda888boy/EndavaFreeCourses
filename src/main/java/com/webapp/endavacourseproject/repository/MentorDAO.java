package com.webapp.endavacourseproject.repository;

import com.webapp.endavacourseproject.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorDAO extends JpaRepository<Mentor, Long> {

    @Query(value = "select * from mentors limit ?1", nativeQuery = true)
    List<Mentor> getAllMentors(Long l);
}
