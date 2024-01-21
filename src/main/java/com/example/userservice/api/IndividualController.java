package com.example.userservice.api;

import com.example.userservice.model.dto.IndividualDTO;
import com.example.userservice.service.IndividualService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/individuals")
public class IndividualController {

    private final IndividualService individualService;

    @Autowired
    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @PostMapping
    public Mono<IndividualDTO> createIndividual(@RequestBody IndividualDTO individualDTO) {
        return individualService.createIndividual(individualDTO);
    }

    @GetMapping
    public Flux<IndividualDTO> getAllIndividuals() {
        return individualService.getAllIndividuals();
    }

    @GetMapping("/{individualId}")
    public Mono<IndividualDTO> getIndividualById(@PathVariable Long individualId) {
        return individualService.getIndividualById(individualId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Individual not found")));
    }

    @PutMapping("/{individualId}")
    public Mono<IndividualDTO> updateIndividual(@RequestBody IndividualDTO individualDTO) {
        return individualService.updateIndividual(individualDTO);
    }

    @DeleteMapping("/{individualId}")
    public Mono<Void> deleteIndividual(@PathVariable Long individualId) {
        return individualService.deleteIndividual(individualId);
    }
}
