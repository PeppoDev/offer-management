package com.offer.management.offermanagement.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.offer.management.offermanagement.modules.candidate.CandidateRepository;
import com.offer.management.offermanagement.modules.candidate.dto.ProfileCandidateReponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateReponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        var candidateDTO = ProfileCandidateReponseDTO.builder().description(candidate.getDescription())
                .id(candidate.getId()).username(candidate.getUsername()).name(candidate.getName()).build();

        return candidateDTO;
    }
}
