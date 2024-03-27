package com.offer.management.offermanagement.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offer.management.offermanagement.exceptions.UserAlreadyExistsException;
import com.offer.management.offermanagement.modules.candidate.CandidateEntity;
import com.offer.management.offermanagement.modules.candidate.CandidateRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public @Valid CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {

        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException();
                });

        return this.candidateRepository.save(candidateEntity);
    }
}