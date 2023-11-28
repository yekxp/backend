package com.developers.hireasenior.mapper;

import com.developers.hireasenior.dto.TechnologyDto;
import com.developers.hireasenior.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TechnologyMapperTest {

    @InjectMocks
    private TechnologyMapper technologyMapper = TechnologyMapper.INSTANCE;
    @Mock
    private Technology java;
    @Mock
    private Technology python;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        java = new Technology();
        java.setName("Java");
        java.setCode("java");
        java.setDescription("Java programming language");

        python = new Technology();
        python.setName("Python");
        python.setCode("python");
        python.setDescription("Python programming language");
    }

    @Test
    public void testTechnologyDtoToTechnology() {
        TechnologyDto technologyDto = new TechnologyDto("Java", "java", "Java programming language");

        Technology technology = technologyMapper.technologyDtoToTechnology(technologyDto);

        assertEquals(technologyDto.getName(), technology.getName());
        assertEquals(technologyDto.getCode(), technology.getCode());
        assertEquals(technologyDto.getDescription(), technology.getDescription());
    }

    @Test
    public void testTechnologySetToDtoSet() {
        Set<Technology> technologySet = new HashSet<>();

        technologySet.add(java);
        technologySet.add(python);

        Set<TechnologyDto> technologyDtoSet = technologyMapper.technologySetToDtoSet(technologySet);

        assertEquals(technologySet.size(), technologyDtoSet.size());
    }

    @Test
    public void testTechnologyDtoSetToTechnologySet() {
        Set<TechnologyDto> technologyDtoSet = new HashSet<>();
        technologyDtoSet.add(new TechnologyDto("Java", "java", "Java programming language"));
        technologyDtoSet.add(new TechnologyDto("Python", "python", "Python programming language"));

        Set<Technology> technologySet = technologyMapper.technologyDtoSetToTechnologySet(technologyDtoSet);

        assertEquals(technologyDtoSet.size(), technologySet.size());
    }
}
