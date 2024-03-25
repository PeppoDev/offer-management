package com.offer.management.offermanagement.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    private UUID id;
    @Pattern(regexp = "^(?!\\s*$).+", message = "invalid username")
    private String name;
    private String username;
    @Email(message = "should cointain a valid email")
    private String email;
    @Length(max = 100, min = 100)
    private String password;
    private String description;
    private String curriculum;

}
