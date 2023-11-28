package com.developers.hireasenior.service;
import com.developers.hireasenior.dto.request.AddTechnologyRequest;
import com.developers.hireasenior.dto.request.DeleteTechnologyRequest;
import com.developers.hireasenior.dto.request.UpdateTechnologyRequest;
import com.developers.hireasenior.dto.response.*;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TechnologyServiceTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyService technologyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTechnology_Success() {
        AddTechnologyRequest request = new AddTechnologyRequest("Java", "JAVA123", "Programming language");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenReturn(null);

        ApiResponse<AddTechnologyResponse> response = technologyService.addTechnology(request);

        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals("Technology created successfully.", response.getMessage());
        verify(technologyRepository).save(any(Technology.class));
    }

    @Test
    void testAddTechnology_Failure_AlreadyExists() {
        AddTechnologyRequest request = new AddTechnologyRequest("Java", "JAVA123", "Programming language");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenReturn(new Technology());

        ApiResponse<AddTechnologyResponse> response = technologyService.addTechnology(request);

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals("Resource already exists: technology", response.getMessage());
    }

    @Test
    void testAddTechnology_Failure_UnknownError() {
        AddTechnologyRequest request = new AddTechnologyRequest("Java", "JAVA123", "Programming language");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenThrow(new RuntimeException("Test Exception"));

        ApiResponse<AddTechnologyResponse> response = technologyService.addTechnology(request);

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals("An unknown error occurred when creating technology.", response.getMessage());
    }

    @Test
    void testListTechnologies_Success() {
        List<Technology> technologies = Arrays.asList(
                new Technology("Java", "JAVA123", "https://example.com/java.jpg", "Programming language"),
                new Technology("Python", "PYTHON456", "https://example.com/python.jpg", "Scripting language")
        );
        when(technologyRepository.findAll()).thenReturn(technologies);

        ApiResponse<ListTechnologiesResponse> response = technologyService.listTechnologies();

        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals(technologies.size(), response.getData().getTechnologies().size());
        assertEquals("Technologies successfully listed.", response.getMessage());
    }

    @Test
    void testListTechnologies_Failure_UnknownError() {
        when(technologyRepository.findAll()).thenThrow(new RuntimeException("Test Exception"));

        ApiResponse<ListTechnologiesResponse> response = technologyService.listTechnologies();

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals("An unknown error occurred when listing technologies.", response.getMessage());
    }

    @Test
    void testUpdateTechnology_Success() {
        UpdateTechnologyRequest request = new UpdateTechnologyRequest("Java", "Updated Java", "Updated description", "https://example.com/updated-java.jpg");
        Technology existingTechnology = new Technology("Java", "JAVA123", "https://example.com/java.jpg", "Programming language");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenReturn(existingTechnology);

        ApiResponse<UpdateTechnologyResponse> response = technologyService.updateTechnology(request);

        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals("Technology updated successfully.", response.getMessage());
        verify(technologyRepository).save(eq(existingTechnology));
    }

    @Test
    void testUpdateTechnology_Failure_NotFound() {
        UpdateTechnologyRequest request = new UpdateTechnologyRequest("Java", "Updated Java", "Updated description", "https://example.com/updated-java.jpg");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenReturn(null);

        ApiResponse<UpdateTechnologyResponse> response = technologyService.updateTechnology(request);

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertTrue(response.getMessage().contains("Technology not found"));
    }

    @Test
    void testUpdateTechnology_Failure_UnknownError() {
        UpdateTechnologyRequest request = new UpdateTechnologyRequest("Java", "Updated Java", "Updated description", "https://example.com/updated-java.jpg");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenThrow(new RuntimeException("Test Exception"));

        ApiResponse<UpdateTechnologyResponse> response = technologyService.updateTechnology(request);

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals("An unknown error occurred when updating technology.", response.getMessage());
    }

    @Test
    void testDeleteTechnology_Success() {
        DeleteTechnologyRequest request = new DeleteTechnologyRequest("JAVA123");
        Technology existingTechnology = new Technology("Java", "JAVA123", "https://example.com/java.jpg", "Programming language");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenReturn(existingTechnology);

        ApiResponse<DeleteTechnologyResponse> response = technologyService.deleteTechnology(request);

        assertTrue(response.isSuccess());
        assertNotNull(response.getData());
        assertEquals("Technology deleted successfully.", response.getMessage());
        verify(technologyRepository).delete(eq(existingTechnology));
    }

    @Test
    void testDeleteTechnology_Failure_NotFound() {
        DeleteTechnologyRequest request = new DeleteTechnologyRequest("JAVA123");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenReturn(null);

        ApiResponse<DeleteTechnologyResponse> response = technologyService.deleteTechnology(request);

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertTrue(response.getMessage().contains("Technology not found"));
    }

    @Test
    void testDeleteTechnology_Failure_UnknownError() {
        DeleteTechnologyRequest request = new DeleteTechnologyRequest("JAVA123");
        when(technologyRepository.findTechnologyByCode(eq(request.getCode()))).thenThrow(new RuntimeException("Test Exception"));

        ApiResponse<DeleteTechnologyResponse> response = technologyService.deleteTechnology(request);

        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals("An unknown error occurred when deleting technology.", response.getMessage());
    }
}

