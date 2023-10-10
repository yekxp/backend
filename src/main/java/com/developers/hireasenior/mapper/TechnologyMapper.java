package com.developers.hireasenior.mapper;

import com.developers.hireasenior.dto.TechnologyDto;
import com.developers.hireasenior.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TechnologyMapper {

    TechnologyMapper INSTANCE = Mappers.getMapper(TechnologyMapper.class);
    TechnologyDto technologyEntityToDto(Technology technology);
    Technology technologyDtoToTechnology(TechnologyDto technologyDto);

    Set<TechnologyDto> technologySetToDtoSet(Set<Technology> technologies);
    Set<Technology> technologyDtoSetToTechnologySet(Set<TechnologyDto> technologyDtoSet);

}
