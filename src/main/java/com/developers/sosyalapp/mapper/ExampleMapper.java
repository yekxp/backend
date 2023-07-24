package com.developers.sosyalapp.mapper;

import com.developers.sosyalapp.dto.ExampleDto;
import com.developers.sosyalapp.model.Example;
import org.springframework.stereotype.Component;

@Component
public class ExampleMapper {
    public ExampleDto toDto(Example entity) {
        ExampleDto dto = new ExampleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public Example toEntity(ExampleDto dto) {
        Example entity = new Example();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
