package com.plexus.plextalent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.plexus.plextalent.model.HrProviderCompany;
import com.plexus.plextalent.repository.HrProviderCompanyRepository;

@ExtendWith(MockitoExtension.class)
class HrProviderCompanyServiceTest {

    @Mock
    private HrProviderCompanyRepository companyRepository;

    @InjectMocks
    private HrProviderCompanyServiceImpl companyServiceImpl;

    @Test
    void saveHrProviderCompany() {
        HrProviderCompany company = new HrProviderCompany();
        companyServiceImpl.saveHrProviderCompany(company);
        verify(companyRepository).save(company);
    }

    @Test
    void getAllHrProviderCompaniesWithData() {
        List<HrProviderCompany> simulatedCollection = new ArrayList<>();
        simulatedCollection.add(new HrProviderCompany(1L, "Company1", "Description1"));
        simulatedCollection.add(new HrProviderCompany(2L, "Company2", "Description2"));

        List<HrProviderCompany> expected = new ArrayList<>(simulatedCollection);

        Mockito.when(companyRepository.findAll()).thenReturn(simulatedCollection);

        final List<HrProviderCompany> result = companyServiceImpl.getAllHrProviderCompanies();
        assertEquals(expected, result);
    }

    @Test
    void getAllHrProviderCompaniesWithoutData() {
        List<HrProviderCompany> simulatedCollection = new ArrayList<>();
        List<HrProviderCompany> expectedCollection = new ArrayList<>();

        Mockito.when(companyRepository.findAll()).thenReturn(simulatedCollection);

        final List<HrProviderCompany> result = companyServiceImpl.getAllHrProviderCompanies();
        assertEquals(expectedCollection, result);
    }

    @Test
    void getHrProviderCompanyByIdExistenceId() {
        HrProviderCompany simulatedObject = new HrProviderCompany(1L, "Company1", "Description1");
        HrProviderCompany expected = new HrProviderCompany(1L, "Company1", "Description1");

        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(simulatedObject));

        final HrProviderCompany result = companyServiceImpl.getHrProviderCompanyById(1L);
        assertEquals(expected, result);
    }

    @Test
    void getHrProviderCompanyByIdNonExistenceId() {
        Mockito.when(companyRepository.findById(-1L)).thenReturn(Optional.empty());
        final HrProviderCompany result = companyServiceImpl.getHrProviderCompanyById(-1L);
        assertNull(result);
    }

    @Test
    void updateHrProviderCompanyExistenceId() {
        HrProviderCompany updatedCompany = new HrProviderCompany(1L, "UpdatedCompany", "UpdatedDescription");

        Mockito.when(companyRepository.existsById(1L)).thenReturn(true);

        companyServiceImpl.updateHrProviderCompany(1L, updatedCompany);
        verify(companyRepository).save(updatedCompany);
    }

    @Test
    void updateHrProviderCompanyNonExistenceId() {
        HrProviderCompany updatedCompany = new HrProviderCompany(1L, "UpdatedCompany", "UpdatedDescription");

        Mockito.when(companyRepository.existsById(1L)).thenReturn(false);

        companyServiceImpl.updateHrProviderCompany(1L, updatedCompany);
        verify(companyRepository, Mockito.never()).save(any());
    }

    @Test
    void deleteHrProviderCompanyExistenceId() {
        Mockito.when(companyRepository.existsById(1L)).thenReturn(true);
        companyServiceImpl.deleteHrProviderCompany(1L);
        verify(companyRepository).deleteById(1L);
    }

    @Test
    void deleteHrProviderCompanyNonExistenceId() {
        Mockito.when(companyRepository.existsById(1L)).thenReturn(false);
        companyServiceImpl.deleteHrProviderCompany(1L);
        verify(companyRepository, Mockito.never()).deleteById(any());
    }
}
