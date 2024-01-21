package com.example.userservice.model.mapper;

import com.example.userservice.model.dto.ProfileDTO;
import com.example.userservice.model.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProfileMapper {

    ProfileEntity profileDtoToEntity(ProfileDTO profileDTO);

    ProfileDTO profileEntityToDto(ProfileEntity profileEntity);
}
