package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "person.operations_history_keys")
public class OperationsHistoryKeysEntity {
    @Id
    private Long id;
    private String profileType;
    private String screen;
    private String key;
    private String description;
    private String language;
}
