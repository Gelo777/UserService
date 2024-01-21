package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "person.profile_required_fields")
public class ProfileRequiredFieldEntity {
    @Id
    private UUID uid;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String countryAlpha3Code;
    private String validationType;
    private String validationRules;
    private String dataType;
    private String key;
    private String language;
    private String representationName;
    private String description;
    private String defaultValue;
    private String profileType;
    private Long sortingIndex;
    private String placeholder;
}
