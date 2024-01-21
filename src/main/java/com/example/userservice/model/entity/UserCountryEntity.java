package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "person.users_countries")
public class UserCountryEntity {
    @Id
    private Long id;
    private UUID userUid;
    private Long countryId;

    @ManyToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CountryEntity country;
}
