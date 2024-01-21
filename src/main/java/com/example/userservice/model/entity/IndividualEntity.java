package com.example.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "person.individuals")
public class IndividualEntity {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private String passportNumber;
    private String personalIdentityNumber;
    private String gender;
    private String status;
    private LocalDateTime verifiedAt;
    private LocalDateTime archivedAt;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "profile_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private ProfileEntity profile;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AddressEntity address;
}
