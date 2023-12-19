package com.plexus.plextalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plexus.plextalent.model.HrInterviewer;

/**
 * This interface inherits from the parent JpaRepository class to provide the CRUD methods of the database for the HrInterviewer entity.
 */
@Repository
public interface HrInterviewerRepository extends JpaRepository<HrInterviewer, Long> {
}
