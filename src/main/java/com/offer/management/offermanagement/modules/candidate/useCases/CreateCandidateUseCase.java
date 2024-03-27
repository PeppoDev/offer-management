package com.offer.management.offermanagement.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offer.management.offermanagement.exceptions.UserAlreadyExistsException;
import com.offer.management.offermanagement.modules.candidate.CandidateEntity;
import com.offer.management.offermanagement.modules.candidate.CandidateRepository;

@Service 
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException();
                });

        return this.candidateRepository.save(candidateEntity);
    }
}
