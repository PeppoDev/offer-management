package com.offer.management.offermanagement.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offer.management.offermanagement.exceptions.UserAlreadyExistsException;
import com.offer.management.offermanagement.modules.company.entities.CompanyEntity;
import com.offer.management.offermanagement.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(),
                companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException();
                });

        System.out.println(companyEntity);

        return this.companyRepository.save(companyEntity);
    }
}
