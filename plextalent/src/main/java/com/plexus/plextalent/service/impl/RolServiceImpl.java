package com.plexus.plextalent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plexus.plextalent.model.Rol;
import com.plexus.plextalent.repository.RolRepository;
import com.plexus.plextalent.service.RolService;

/**
 * This class implements RolServiceInterface
 */
@Service
public class RolServiceImpl implements RolService {
	private final RolRepository rolRepository;

	/**
	 * Constructor of rolRepositoryImpl
	 * 
	 * @param rolRepository This param is an object of RolRepository
	 */
	public RolServiceImpl(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}

	/**
	 * This method creates a new roles record in db
	 * 
	 * @param rol This param is an object of rol
	 */
	public void save(Rol rol) {
		rolRepository.save(rol);
	}

	/**
	 * This method returns a list of all db roles records
	 * 
	 * @return method rolRepository.findAll()
	 */
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	/**
	 * This method returns a roles record lloking for it by id
	 * 
	 * @param id This param is the id of the db roles
	 * @return method rolRepository.findById()
	 */
	public Optional<Rol> findById(Long id) {
		return rolRepository.findById(id);
	}

	/**
	 * This method deletes a roles record looking for it by id
	 * 
	 * @param id This param is the id of the db roles
	 */

	public void deleteById(Long id) {
		if (rolRepository.existsById(id)) {
			rolRepository.deleteById(id);
		}
	}
}
