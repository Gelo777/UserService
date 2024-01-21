package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "person.countries")
public class CountryEntity {
    @Id

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String name;
    private String alpha3;
    private boolean isActive;
    private String alpha2;
}
