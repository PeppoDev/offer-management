package com.offer.management.offermanagement.modules.candidate.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileCandidateReponseDTO {
    private String description;
    private String username;
    private UUID id;
    private String name;
}
