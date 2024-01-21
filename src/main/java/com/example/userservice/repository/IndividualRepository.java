package com.example.userservice.repository;

import com.example.userservice.model.entity.IndividualEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IndividualRepository extends ReactiveCrudRepository<IndividualEntity, Long> {
}
