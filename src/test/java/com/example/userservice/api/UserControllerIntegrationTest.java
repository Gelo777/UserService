package com.example.userservice.api;

import com.example.userservice.model.dto.UserDTO;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec;
@ExtendWith(SpringExtension.class)
@WebFluxTest(UserController.class)
@Testcontainers
public class UserControllerIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
        .withDatabaseName("testdb")
        .withUsername("testuser")
        .withPassword("testpassword");

    @Mock
    private UserService userService;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
    }

    @Test
    void testCreateUser() {
        UserDTO userDTO = createSampleUserDTO();
        Mockito.when(userService.createUser(userDTO)).thenReturn(Mono.just(userDTO));

        BodyContentSpec bodyContentSpec = webTestClient.post()
            .uri("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(userDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody();

        bodyContentSpec.jsonPath("$.email").isEqualTo(userDTO.getEmail());

        Mockito.verify(userService, Mockito.times(1)).createUser(userDTO);
    }

    @Test
    void testGetAllUsers() {
        Flux<UserDTO> userFlux = Flux.just(createSampleUserDTO(), createSampleUserDTO());
        Mockito.when(userService.getAllUsers()).thenReturn(userFlux);

        BodyContentSpec responseSpec = webTestClient.get()
            .uri("/api/users")
            .exchange()
            .expectStatus().isOk()
            .expectBody();

        responseSpec.jsonPath("$.length()").isEqualTo(userFlux.collectList().block().size());

        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = createSampleUserDTO();
        Mockito.when(userService.getUserById(userId)).thenReturn(Mono.just(userDTO));

        BodyContentSpec responseSpec = webTestClient.get()
            .uri("/api/users/{userId}", userId)
            .exchange()
            .expectStatus().isOk()
            .expectBody();

        responseSpec.jsonPath("$.email").isEqualTo(userDTO.getEmail());

        Mockito.verify(userService, Mockito.times(1)).getUserById(userId);
    }

    @Test
    void testUpdateUser() {
        UUID userId = UUID.randomUUID();
        UserDTO updatedUserDTO = createSampleUserDTO();
        updatedUserDTO.setUid(userId);
        Mockito.when(userService.updateUser(updatedUserDTO)).thenReturn(Mono.just(updatedUserDTO));

        WebTestClient.BodyContentSpec bodyContentSpec = webTestClient.put()
            .uri("/api/users/{userId}", userId)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedUserDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody();

        bodyContentSpec.jsonPath("$.email").isEqualTo(updatedUserDTO.getEmail());

        Mockito.verify(userService, Mockito.times(1)).updateUser(updatedUserDTO);
    }

    @Test
    void testDeleteUser() {
        UUID userId = UUID.randomUUID();

        webTestClient.delete()
            .uri("/api/users/{userId}", userId)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Void.class);
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
}