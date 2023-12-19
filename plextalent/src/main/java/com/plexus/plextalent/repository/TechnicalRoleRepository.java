package com.plexus.plextalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plexus.plextalent.model.TechnicalRole;

/**
 * This interface inherits from the parent JpaRepository class to provide the CRUD methods of the database for the TechnicalRole entity.
 */
public interface TechnicalRoleRepository extends JpaRepository<TechnicalRole, Long> {
}
