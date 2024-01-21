package com.example.userservice.service;

import com.example.userservice.model.dto.IndividualDTO;
import com.example.userservice.model.entity.IndividualEntity;
import com.example.userservice.model.mapper.IndividualMapper;
import com.example.userservice.repository.IndividualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IndividualService {

    private final IndividualRepository individualRepository;
    private final IndividualMapper individualMapper;

    public Mono<IndividualDTO> createIndividual(IndividualDTO individualDTO) {
        IndividualEntity individualEntity = individualMapper.individualDtoToEntity(individualDTO);
        individualEntity.setCreatedAt(LocalDateTime.now());
        individualEntity.setModifiedAt(LocalDateTime.now());

        return individualRepository.save(individualEntity)
            .map(individualMapper::individualEntityToDto);
    }

    public Flux<IndividualDTO> getAllIndividuals() {
        return individualRepository.findAll()
            .map(individualMapper::individualEntityToDto);
    }

    public Mono<IndividualDTO> getIndividualById(Long individualId) {
        return individualRepository.findById(individualId)
            .map(individualMapper::individualEntityToDto);
    }

    public Mono<IndividualDTO> updateIndividual(IndividualDTO individualDTO) {
        IndividualEntity individualEntity = individualMapper.individualDtoToEntity(individualDTO);
        individualEntity.setModifiedAt(LocalDateTime.now());
        return individualRepository.save(individualEntity)
            .map(individualMapper::individualEntityToDto);
    }

    public Mono<Void> deleteIndividual(Long individualId) {
        return individualRepository.deleteById(individualId);
    }
}