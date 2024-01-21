package com.example.userservice.api;

import com.example.userservice.model.dto.ProfileDTO;
import com.example.userservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public Mono<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        return profileService.createProfile(profileDTO);
    }

    @GetMapping
    public Flux<ProfileDTO> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{profileId}")
    public Mono<ProfileDTO> getProfileById(@PathVariable UUID profileId) {
        return profileService.getProfileById(profileId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found")));
    }

    @PutMapping("/{profileId}")
    public Mono<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) {
        return profileService.updateProfile(profileDTO);
    }

    @DeleteMapping("/{profileId}")
    public Mono<Void> deleteProfile(@PathVariable UUID profileId) {
        return profileService.deleteProfile(profileId);
    }
}
