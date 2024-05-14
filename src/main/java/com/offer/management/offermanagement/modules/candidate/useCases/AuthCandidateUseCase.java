package com.offer.management.offermanagement.modules.candidate.useCases;

import java.time.Instant;
import java.util.Arrays;
import java.time.Duration;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.offer.management.offermanagement.modules.candidate.CandidateRepository;
import com.offer.management.offermanagement.modules.candidate.dto.AuthCandidateRequestDTO;
import com.offer.management.offermanagement.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO)
            throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });

        var matches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!matches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create().withIssuer("javagas").withSubject(candidate.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("CANDIDATE")).sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponse;
    }
}
