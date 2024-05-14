package com.offer.management.offermanagement.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offer.management.offermanagement.modules.company.dto.AuthCompanyRequestDTO;
import com.offer.management.offermanagement.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyRequestDTO authCompanyDTO) {
        try {
            var token = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
