package com.example.userservice.model.mapper;

import com.example.userservice.model.dto.UserDTO;
import com.example.userservice.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface UserMapper {

    UserEntity userDtoToEntity(UserDTO userDTO);

    UserDTO userEntityToDto(UserEntity userEntity);

    UserEntity updateUserFromDto(UserDTO userDTO, @MappingTarget UserEntity userEntity);
}
