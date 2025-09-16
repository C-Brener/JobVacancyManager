package br.com.caiquebrener.job_vacancy_manager.modules.company.usecase;

import br.com.caiquebrener.job_vacancy_manager.modules.company.dto.AuthCompanyDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.company.dto.AuthCompanyResponseDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.company.repository.ICompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private ICompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO dto) throws AuthenticationException {
        var company = this.repository.findByUserName(dto.getUserName()).orElseThrow(
                () -> new UsernameNotFoundException("Username or Password incorrect")
        );
        var passwordMatches = this.passwordEncoder.matches(dto.getPassword(), company.getPassword());
        // Aqui precisaremos criar uma criptografia para verificar a senha que está sendo passada é a mesma que a que esta no banco de dados
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("jobVacancy")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO
                .builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
