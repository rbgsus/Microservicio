package com.plexus.plextalent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plexus.plextalent.model.TechnicalRole;
import com.plexus.plextalent.repository.TechnicalRoleRepository;
import com.plexus.plextalent.service.TechnicalRoleService;

/**
 * This class implements TechnicalRoleServiceInterface
 */
@Service
public class TechnicalRoleServiceImpl implements TechnicalRoleService {

    private final TechnicalRoleRepository technicalRoleRepository;

    /**
     * Constructor of TechnicalRoleServiceImpl
     *
     * @param technicalRoleRepository This param is an object of TechnicalRoleRepository
     */
    public TechnicalRoleServiceImpl(TechnicalRoleRepository technicalRoleRepository) {
        this.technicalRoleRepository = technicalRoleRepository;
    }

    /**
     * This method creates a new technical role record in the database
     *
     * @param technicalRole This param is an object of technical role
     */
    @Override
    public void saveTechnicalRole(TechnicalRole technicalRole) {
        technicalRoleRepository.save(technicalRole);
    }

    /**
     * This method returns a list of all technical role records in the database
     *
     * @return method technicalRoleRepository.findAll()
     */
    @Override
    public List<TechnicalRole> getAllTechnicalRoles() {
        return technicalRoleRepository.findAll();
    }

    /**
     * This method returns a technical role record by looking for it by id
     *
     * @param id This param is the id of the technical role record in the database
     * @return method technicalRoleRepository.findById()
     */
    @Override
    public Optional<TechnicalRole> getTechnicalRoleById(Long id) {
        return technicalRoleRepository.findById(id);
    }

    /**
     * This method updates a technical role record by its id
     *
     * @param id             This param is the id of the technical role record in the database
     * @param technicalRole This param is the updated object of technical role
     */
    @Override
    public void updateTechnicalRole(Long id, TechnicalRole technicalRole) {
        if (technicalRoleRepository.existsById(id)) {
            technicalRole.setId(id);
            technicalRoleRepository.save(technicalRole);
        }
    }

    /**
     * This method deletes a technical role record by its id
     *
     * @param id This param is the id of the technical role record in the database
     */
    @Override
    public void deleteTechnicalRole(Long id) {
        if (technicalRoleRepository.existsById(id)) {
            technicalRoleRepository.deleteById(id);
        }
    }
}
