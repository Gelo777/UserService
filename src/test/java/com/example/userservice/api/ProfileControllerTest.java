package com.example.userservice.api;

import com.example.userservice.model.dto.ProfileDTO;
import com.example.userservice.model.dto.UserDTO;
import com.example.userservice.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @Test
    void testCreateProfile() {
        ProfileDTO profileDTO = createSampleProfileDTO();
        when(profileService.createProfile(profileDTO)).thenReturn(Mono.just(profileDTO));

        WebTestClient.bindToController(profileController).build()
            .post()
            .uri("/api/profiles")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(profileDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ProfileDTO.class)
            .isEqualTo(profileDTO);

        verify(profileService, times(1)).createProfile(profileDTO);
    }

    @Test
    void testGetAllProfiles() {
        Flux<ProfileDTO> profileFlux = Flux.just(createSampleProfileDTO(), createSampleProfileDTO());
        when(profileService.getAllProfiles()).thenReturn(profileFlux);

        WebTestClient.bindToController(profileController).build()
            .get()
            .uri("/api/profiles")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(ProfileDTO.class)
            .hasSize(2);

        verify(profileService, times(1)).getAllProfiles();
    }

    @Test
    void testGetProfileById() {
        UUID profileId = UUID.randomUUID();
        ProfileDTO profileDTO = createSampleProfileDTO();
        when(profileService.getProfileById(profileId)).thenReturn(Mono.just(profileDTO));

        WebTestClient.bindToController(profileController).build()
            .get()
            .uri("/api/profiles/{profileId}", profileId)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ProfileDTO.class)
            .isEqualTo(profileDTO);

        verify(profileService, times(1)).getProfileById(profileId);
    }

    @Test
    void testUpdateProfile() {
        ProfileDTO updatedProfileDTO = createSampleProfileDTO();
        when(profileService.updateProfile(updatedProfileDTO)).thenReturn(Mono.just(updatedProfileDTO));

        WebTestClient.bindToController(profileController).build()
            .put()
            .uri("/api/profiles/{profileId}", updatedProfileDTO.getUid())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedProfileDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ProfileDTO.class)
            .isEqualTo(updatedProfileDTO);

        verify(profileService, times(1)).updateProfile(updatedProfileDTO);
    }

    @Test
    void testDeleteProfile() {
        UUID profileId = UUID.randomUUID();
        when(profileService.deleteProfile(profileId)).thenReturn(Mono.empty());

        WebTestClient.bindToController(profileController).build()
            .delete()
            .uri("/api/profiles/{profileId}", profileId)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Void.class);

        verify(profileService, times(1)).deleteProfile(profileId);
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
}
