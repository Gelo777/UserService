package com.example.userservice.repository;

import com.example.userservice.model.entity.ProfileEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends ReactiveCrudRepository<ProfileEntity, UUID> {

}
