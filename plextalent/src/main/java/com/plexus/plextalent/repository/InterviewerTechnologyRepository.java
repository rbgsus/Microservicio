package com.plexus.plextalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plexus.plextalent.model.InterviewerTechnology;

/**
 * This interface inherits from the parent JpaRepository class to provide the CRUD methods of the database for the InterviewerTechnology entity.
 */
public interface InterviewerTechnologyRepository extends JpaRepository<InterviewerTechnology, Long> {
}
