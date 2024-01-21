package com.example.userservice.model.mapper;

import com.example.userservice.model.dto.IndividualDTO;
import com.example.userservice.model.entity.IndividualEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IndividualMapper {

    IndividualEntity individualDtoToEntity(IndividualDTO individualDTO);

    IndividualDTO individualEntityToDto(IndividualEntity individualEntity);
}
