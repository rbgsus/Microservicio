package com.plexus.plextalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plexus.plextalent.model.Technology;

/**
 * This interface inherits from the parent JpaRepository class to provide the CRUD methods of the database for the Technology entity.
 */
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
