package com.example.userservice.service;

import com.example.userservice.model.dto.ProfileDTO;
import com.example.userservice.model.entity.ProfileEntity;
import com.example.userservice.model.mapper.ProfileMapper;
import com.example.userservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public Mono<ProfileDTO> createProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = profileMapper.profileDtoToEntity(profileDTO);
        profileEntity.setUid(UUID.randomUUID());
        profileEntity.setCreatedAt(LocalDateTime.now());
        profileEntity.setModifiedAt(LocalDateTime.now());

        return profileRepository.save(profileEntity)
            .map(profileMapper::profileEntityToDto);
    }

    public Flux<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll()
            .map(profileMapper::profileEntityToDto);
    }

    public Mono<ProfileDTO> getProfileById(UUID profileId) {
        return profileRepository.findById(profileId)
            .map(profileMapper::profileEntityToDto);
    }

    public Mono<ProfileDTO> updateProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = profileMapper.profileDtoToEntity(profileDTO);
        profileEntity.setModifiedAt(LocalDateTime.now());
        return profileRepository.save(profileEntity)
            .map(profileMapper::profileEntityToDto);
    }

    public Mono<Void> deleteProfile(UUID profileId) {
        return profileRepository.deleteById(profileId);
    }
}
