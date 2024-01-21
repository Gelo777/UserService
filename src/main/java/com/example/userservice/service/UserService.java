package com.example.userservice.service;

import com.example.userservice.model.dto.UserDTO;
import com.example.userservice.model.entity.UserEntity;
import com.example.userservice.model.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<UserDTO> createUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.userDtoToEntity(userDTO);
        userEntity.setUid(UUID.randomUUID());
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setModifiedAt(LocalDateTime.now());

        return userRepository.save(userEntity)
            .map(userMapper::userEntityToDto);
    }

    public Flux<UserDTO> getAllUsers() {
        return userRepository.findAll()
            .map(userMapper::userEntityToDto);
    }

    public Mono<UserDTO> getUserById(UUID userId) {
        return userRepository.findById(userId)
            .map(userMapper::userEntityToDto);
    }

    public Mono<UserDTO> updateUser(UserDTO userDTO) {
        return userRepository.findById(userDTO.getUid())
            .map(existingUserEntity -> {
                userMapper.updateUserFromDto(userDTO, existingUserEntity);
                existingUserEntity.setModifiedAt(LocalDateTime.now());
                return existingUserEntity;
            })
            .flatMap(userRepository::save)
            .map(userMapper::userEntityToDto);
    }

    public Mono<Void> deleteUser(UUID userId) {
        return userRepository.deleteById(userId);
    }
}
