package com.plexus.plextalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plexus.plextalent.model.User;

/**
 * This interface inherits from the parent JpaRepository class to provide the CRUD methods of the db.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

