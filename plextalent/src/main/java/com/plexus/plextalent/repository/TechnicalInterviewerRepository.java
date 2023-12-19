package com.plexus.plextalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plexus.plextalent.model.TechnicalInterviewer;

/**
 * This interface inherits from the parent JpaRepository class to provide the CRUD methods of the database for the TechnicalInterviewer entity.
 */
public interface TechnicalInterviewerRepository extends JpaRepository<TechnicalInterviewer, Long> {
}
