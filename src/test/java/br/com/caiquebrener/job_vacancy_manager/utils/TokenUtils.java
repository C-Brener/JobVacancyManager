package br.com.caiquebrener.job_vacancy_manager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class TokenUtils {

    // WHEN Import this project in other local it will be necessary create this secret variable
    // This file it will be created only for test
    @Value("${security.token.secret}")
    private String secretKey;

    public String generateToken(UUID company) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        return JWT.create()
                .withIssuer("jobVacancy")
                .withExpiresAt(expiresIn)
                .withSubject(company.toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);
    }
}