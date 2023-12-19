package com.plexus.plextalent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.plexus.plextalent.model.Technology;
import com.plexus.plextalent.repository.TechnologyRepository;

public class TechnologyServiceTest {

	@Mock
	private TechnologyRepository technologyRepository;

	@InjectMocks
	private TechnologyServiceImpl technologyService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllTechnologies() {
		List<Technology> technologyList = new ArrayList<>();
		technologyList.add(new Technology(1L, "Java", "Programming language"));
		technologyList.add(new Technology(2L, "Python", "Scripting language"));
		when(technologyRepository.findAll()).thenReturn(technologyList);
		List<Technology> result = technologyService.getAllTechnologies();
		assertEquals(2, result.size());
		verify(technologyRepository, times(1)).findAll();
	}

	@Test
	void getFindAllTechnologiesWithData() {
		List<Technology> simulatedCollection = new ArrayList<>();
		simulatedCollection.add(new Technology(1L, "Java", "Programming language"));
		simulatedCollection.add(new Technology(2L, "Python", "Scripting language"));

		List<Technology> expected = new ArrayList<>();
		expected.add(new Technology(1L, "Java", "Programming language"));
		expected.add(new Technology(2L, "Python", "Scripting language"));

		Mockito.when(technologyRepository.findAll()).thenReturn(simulatedCollection);

		final List<Technology> result = technologyService.getAllTechnologies();
		assertEquals(expected, result);
	}

	@Test
	void getTechnologyById() {
		Technology technology = new Technology(1L, "Java", "Programming language");
		Optional<Technology> optionalTechnology = Optional.of(technology);
		when(technologyRepository.findById(1L)).thenReturn(optionalTechnology);
		Optional<Technology> result = technologyService.getTechnologyById(1L);
		assertEquals(optionalTechnology, result);
		verify(technologyRepository, times(1)).findById(1L);
	}

	@Test
    void getTechnologyById_NotFound() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Technology> result = technologyService.getTechnologyById(1L);
        assertEquals(Optional.empty(), result);
        verify(technologyRepository, times(1)).findById(1L);
    }

	@Test
	void saveTechnology() {
		Technology technology = new Technology(1L, "Java", "Programming language");
		technologyService.saveTechnology(technology);
		verify(technologyRepository, times(1)).save(technology);
	}

	@Test
	void updateTechnology() {
		Technology updatedTechnology = new Technology(1L, "Python", "Scripting language");
		when(technologyRepository.existsById(1L)).thenReturn(true);
		technologyService.updateTechnology(1L, updatedTechnology);
		verify(technologyRepository, times(1)).save(updatedTechnology);
	}

	@Test
	void updateTechnology_NotFound() {
		Technology updatedTechnology = new Technology(1L, "Python", "Scripting language");

		when(technologyRepository.findById(1L)).thenReturn(Optional.empty());

		verify(technologyRepository, times(0)).save(updatedTechnology);
	}

	@Test
    void deleteTechnology() {
        when(technologyRepository.existsById(1L)).thenReturn(true);
        technologyService.deleteTechnology(1L);
        verify(technologyRepository, times(1)).deleteById(1L);
    }

	@Test
    void deleteTechnology_NotFound() {
        when(technologyRepository.existsById(1L)).thenReturn(false);
        technologyService.deleteTechnology(1L);
        verify(technologyRepository, times(0)).deleteById(1L);
    }
}
