package com.example.userservice.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class IndividualDTO {
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private String passportNumber;
    private String personalIdentityNumber;
    private String gender;
    private String status;
    private LocalDateTime verifiedAt;
    private String email;
    private String phone;
    private boolean filled;
    private UUID profileUid;
    private String applicantId;
    private Long addressId;
}
