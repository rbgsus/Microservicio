package com.plexus.plextalent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plexus.plextalent.model.Technology;
import com.plexus.plextalent.repository.TechnologyRepository;
import com.plexus.plextalent.service.TechnologyService;

/**
 * This class implements TechnologyServiceInterface
 */
@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    /**
     * Constructor of TechnologyServiceImpl
     *
     * @param technologyRepository This param is an object of TechnologyRepository
     */
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    /**
     * This method creates a new technology record in the database
     *
     * @param technology This param is an object of technology
     */
    @Override
    public void saveTechnology(Technology technology) {
        technologyRepository.save(technology);
    }

    /**
     * This method returns a list of all technology records in the database
     *
     * @return method technologyRepository.findAll()
     */
    @Override
    public List<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    /**
     * This method returns a technology record by looking for it by id
     *
     * @param id This param is the id of the technology record in the database
     * @return method technologyRepository.findById()
     */
    @Override
    public Optional<Technology> getTechnologyById(Long id) {
        return technologyRepository.findById(id);
    }

    /**
     * This method updates a technology record by its id
     *
     * @param id        This param is the id of the technology record in the database
     * @param technology This param is the updated object of technology
     */
    @Override
    public void updateTechnology(Long id, Technology technology) {
        if (technologyRepository.existsById(id)) {
            technology.setId(id);
            technologyRepository.save(technology);
        }
    }

    /**
     * This method deletes a technology record by its id
     *
     * @param id This param is the id of the technology record in the database
     */
    @Override
    public void deleteTechnology(Long id) {
        if (technologyRepository.existsById(id)) {
            technologyRepository.deleteById(id);
        }
    }
}
