package com.example.userservice.service;

import com.example.userservice.model.dto.UserDTO;
import com.example.userservice.model.entity.UserEntity;
import com.example.userservice.model.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @Test
    public void createUser() {
        UserDTO userDTO = createSampleUserDTO();
        UserEntity userEntity = createSampleUserEntity();

        when(userMapper.userDtoToEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(Mono.just(userEntity));
        when(userMapper.userEntityToDto(userEntity)).thenReturn(userDTO);

        userService.createUser(userDTO)
            .subscribe(savedUserDTO -> {
                verify(userMapper, times(1)).userDtoToEntity(userDTO);
                verify(userRepository, times(1)).save(userEntity);
                verify(userMapper, times(1)).userEntityToDto(userEntity);
            });
    }

    @Test
    public void getAllUsers() {
        UserEntity userEntity1 = createSampleUserEntity();
        UserEntity userEntity2 = createSampleUserEntity();

        when(userRepository.findAll()).thenReturn(Flux.just(userEntity1, userEntity2));
        when(userMapper.userEntityToDto(userEntity1)).thenReturn(createSampleUserDTO());
        when(userMapper.userEntityToDto(userEntity2)).thenReturn(createSampleUserDTO());

        userService.getAllUsers()
            .collectList()
            .subscribe(users -> {
                verify(userRepository, times(1)).findAll();
                verify(userMapper, times(2)).userEntityToDto(any(UserEntity.class));
            });
    }

    @Test
    public void getUserById() {
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = createSampleUserEntity();

        when(userRepository.findById(userId)).thenReturn(Mono.just(userEntity));
        when(userMapper.userEntityToDto(userEntity)).thenReturn(createSampleUserDTO());

        userService.getUserById(userId)
            .subscribe(foundUserDTO -> {
                verify(userRepository, times(1)).findById(userId);
                verify(userMapper, times(1)).userEntityToDto(userEntity);
            });
    }

    @Test
    public void updateUser() {
        UserDTO inputUserDTO = createSampleUserDTO();
        UserEntity existingUserEntity = createSampleUserEntity();

        when(userRepository.findById(inputUserDTO.getUid())).thenReturn(Mono.just(existingUserEntity));
        when(userMapper.updateUserFromDto(inputUserDTO, existingUserEntity)).thenReturn(existingUserEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(Mono.just(existingUserEntity));

        userService.updateUser(inputUserDTO)
            .subscribe(updatedUserDTO -> {
                verify(userRepository, times(1)).findById(inputUserDTO.getUid());
                verify(userMapper, times(1)).updateUserFromDto(inputUserDTO, existingUserEntity);
                verify(userRepository, times(1)).save(userEntityCaptor.capture());

                UserEntity savedUserEntity = userEntityCaptor.getValue();
                assertEquals(inputUserDTO.getUid(), savedUserEntity.getUid());
                assertEquals(inputUserDTO.getEmail(), savedUserEntity.getEmail());
            });
    }

    @Test
    public void deleteUser() {
        UUID userId = UUID.randomUUID();

        when(userRepository.deleteById(userId)).thenReturn(Mono.empty());

        userService.deleteUser(userId)
            .subscribe(deletedUserDTO -> {
                verify(userRepository, times(1)).findById(userId);
                verify(userRepository, times(1)).deleteById(userId);
            });
    }

    private UserDTO createSampleUserDTO() {
        return new UserDTO(
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            "John Doe",
            "john@example.com",
            "password123",
            "123456789",
            true,
            false,
            UUID.randomUUID(),
            "EN",
            false,
            "mobile2fa",
            "INDIVIDUAL",
            true,
            false,
            false,
            "secretKey"
        );
    }

    private UserEntity createSampleUserEntity() {
        return new UserEntity(
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            "John Doe",
            "john@example.com",
            "password123",
            "123456789",
            true,
            false,
            UUID.randomUUID(),
            "EN",
            false,
            "mobile2fa",
            "INDIVIDUAL",
            true,
            false,
            false,
            "secretKey"
        );
    }
}