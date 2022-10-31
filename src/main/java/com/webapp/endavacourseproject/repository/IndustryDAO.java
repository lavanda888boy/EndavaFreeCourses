package com.webapp.endavacourseproject.repository;

import com.webapp.endavacourseproject.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryDAO extends JpaRepository<Industry, Long> {
}
