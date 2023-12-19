package com.plexus.plextalent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.plexus.plextalent.model.Technology;
import com.plexus.plextalent.service.impl.TechnologyServiceImpl;

@ExtendWith(MockitoExtension.class)
class TechnologyControllerTest {

    @Mock
    private TechnologyServiceImpl technologyService;

    @InjectMocks
    private TechnologyController technologyController;

    @Test
    void saveTechnology_Success() {
        Technology technology = new Technology(1L, "Java", "Programming language");
        technologyService.saveTechnology(technology);

        ResponseEntity<?> response = technologyController.saveTechnology(technology);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(technology, response.getBody());
    }


    @Test
    void saveTechnology_ValidationFailure_EmptyName() {
        Technology technology = new Technology(1L, "", "Programming language");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> technologyController.saveTechnology(technology));
        Mockito.verify(technologyService, Mockito.never()).saveTechnology(technology);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void getAllTechnologies_Success() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming language"));
        technologies.add(new Technology(2L, "Python", "Programming language"));
        Mockito.when(technologyService.getAllTechnologies()).thenReturn(technologies);

        ResponseEntity<?> response = technologyController.getAllTechnologies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(technologies, response.getBody());
    }

    @Test
    void getAllTechnologies_NoTechnologiesFound() {
        List<Technology> technologies = new ArrayList<>();
        Mockito.when(technologyService.getAllTechnologies()).thenReturn(technologies);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> technologyController.getAllTechnologies());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void getTechnologyById_Success() {
        Long technologyId = 1L;
        Technology technology = new Technology(technologyId, "Java", "Programming language");
        Mockito.when(technologyService.getTechnologyById(technologyId)).thenReturn(Optional.of(technology));

        ResponseEntity<?> response = technologyController.getTechnologyById(technologyId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(technology, response.getBody());
    }

    @Test
    void getTechnologyById_TechnologyNotFound() {
        Long technologyId = 1L;
        Mockito.when(technologyService.getTechnologyById(technologyId)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> technologyController.getTechnologyById(technologyId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void updateTechnology_Success() {
        Long technologyId = 1L;
        Technology technology = new Technology(technologyId, "Java", "Programming language");
        Mockito.when(technologyService.getTechnologyById(technologyId)).thenReturn(Optional.of(technology));
        
        ResponseEntity<?> response = technologyController.updateTechnology(technologyId, technology);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(technology, response.getBody());
    }

    @Test
    void updateTechnology_ValidationFailure_EmptyName() {
        Long technologyId = 1L;
        Technology technology = new Technology(technologyId, "", "Programming language");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> technologyController.updateTechnology(technologyId, technology));
        Mockito.verify(technologyService, Mockito.never()).updateTechnology(technologyId, technology);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void updateTechnology_TechnologyNotFound() {
        Long technologyId = 1L;
        Technology technology = new Technology(technologyId, "Java", "Programming language");
        Mockito.when(technologyService.getTechnologyById(technologyId)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> technologyController.updateTechnology(technologyId, technology));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void deleteTechnology_Success() {
        Long technologyId = 1L;
        Technology technology = new Technology(technologyId, "Java", "Programming language");
        Mockito.when(technologyService.getTechnologyById(technologyId)).thenReturn(Optional.of(technology));

        ResponseEntity<?> response = technologyController.deleteTechnology(technologyId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteTechnology_TechnologyNotFound() {
        Long technologyId = 1L;
        Mockito.when(technologyService.getTechnologyById(technologyId)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> technologyController.deleteTechnology(technologyId));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}
