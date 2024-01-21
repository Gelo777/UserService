package com.example.userservice.service;

import com.example.userservice.model.dto.ProfileDTO;
import com.example.userservice.model.dto.UserDTO;
import com.example.userservice.model.entity.ProfileEntity;
import com.example.userservice.model.entity.UserEntity;
import com.example.userservice.model.mapper.ProfileMapper;
import com.example.userservice.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileMapper profileMapper;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void createProfile() {
        ProfileDTO profileDTO = createSampleProfileDTO();
        ProfileEntity profileEntity = createSampleProfileEntity();

        when(profileMapper.profileDtoToEntity(profileDTO)).thenReturn(profileEntity);
        when(profileRepository.save(profileEntity)).thenReturn(Mono.just(profileEntity));
        when(profileMapper.profileEntityToDto(profileEntity)).thenReturn(profileDTO);

        profileService.createProfile(profileDTO)
            .subscribe(savedProfileDTO -> {
                verify(profileMapper, times(1)).profileDtoToEntity(profileDTO);
                verify(profileRepository, times(1)).save(profileEntity);
                verify(profileMapper, times(1)).profileEntityToDto(profileEntity);

                assertEquals(profileDTO.getUserUid(), savedProfileDTO.getUserUid());
            });
    }

    @Test
    void getAllProfiles() {
        ProfileEntity profileEntity1 = createSampleProfileEntity();
        ProfileEntity profileEntity2 = createSampleProfileEntity();

        when(profileRepository.findAll()).thenReturn(Flux.just(profileEntity1, profileEntity2));
        when(profileMapper.profileEntityToDto(profileEntity1)).thenReturn(createSampleProfileDTO());
        when(profileMapper.profileEntityToDto(profileEntity2)).thenReturn(createSampleProfileDTO());

        profileService.getAllProfiles()
            .collectList()
            .subscribe(profiles -> {
                verify(profileRepository, times(1)).findAll();
                verify(profileMapper, times(2)).profileEntityToDto(any(ProfileEntity.class));
            });
    }

    @Test
    void getProfileById() {
        UUID profileId = UUID.randomUUID();
        ProfileEntity profileEntity = createSampleProfileEntity();

        when(profileRepository.findById(profileId)).thenReturn(Mono.just(profileEntity));
        when(profileMapper.profileEntityToDto(profileEntity)).thenReturn(createSampleProfileDTO());

        profileService.getProfileById(profileId)
            .subscribe(foundProfileDTO -> {
                verify(profileRepository, times(1)).findById(profileId);
                verify(profileMapper, times(1)).profileEntityToDto(profileEntity);
            });
    }

    @Test
    void updateProfile() {
        ProfileDTO inputProfileDTO = createSampleProfileDTO();
        ProfileEntity existingProfileEntity = createSampleProfileEntity();

        when(profileMapper.profileDtoToEntity(inputProfileDTO)).thenReturn(existingProfileEntity);
        when(profileRepository.save(any(ProfileEntity.class))).thenReturn(Mono.just(existingProfileEntity));

        profileService.updateProfile(inputProfileDTO)
            .subscribe(updatedProfileDTO -> {
                verify(profileRepository, times(1)).findById(inputProfileDTO.getUserUid());
                verify(profileMapper, times(1)).profileDtoToEntity(inputProfileDTO);
                verify(profileRepository, times(1)).save(existingProfileEntity);
            });
    }

    @Test
    void deleteProfile() {
        UUID profileId = UUID.randomUUID();

        when(profileRepository.deleteById(profileId)).thenReturn(Mono.empty());

        profileService.deleteProfile(profileId)
            .subscribe(deletedProfileDTO -> {
                verify(profileRepository, times(1)).deleteById(profileId);
            });
    }

    private ProfileDTO createSampleProfileDTO() {
        return new ProfileDTO(
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            UUID.randomUUID(),
            "ензу",
            true,
            new UserDTO()
        );
    }

    private ProfileEntity createSampleProfileEntity() {
        return new ProfileEntity(
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            UUID.randomUUID(),
            "ензу",
            true,
            new UserEntity()
        );
    }
}
