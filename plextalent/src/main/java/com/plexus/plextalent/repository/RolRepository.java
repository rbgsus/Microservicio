package com.plexus.plextalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plexus.plextalent.model.Rol;

/**
 * This interface inherits from the parent JpaRepository class to provide the CRUD methods od the db
 */
@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
}
