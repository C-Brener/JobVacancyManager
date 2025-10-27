package br.com.caiquebrener.job_vacancy_manager.modules.jobs.controllers;

import br.com.caiquebrener.job_vacancy_manager.exceptions.exceptions.CompanyNotFoundException;
import br.com.caiquebrener.job_vacancy_manager.modules.company.dto.CreateJobDTO;
import br.com.caiquebrener.job_vacancy_manager.modules.company.entities.CompanyEntity;
import br.com.caiquebrener.job_vacancy_manager.modules.company.repository.ICompanyRepository;
import br.com.caiquebrener.job_vacancy_manager.utils.TokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.caiquebrener.job_vacancy_manager.utils.TestUtils.objectToJSON;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Indicate what is the correct application.properties to use for run this test
class JobControllerTest {

    private MockMvc mvc;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private ICompanyRepository companyRepository;


    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void should_be_able_to_create_a_new_job() throws Exception {
        // Create a company and get you id
        var company = CompanyEntity
                .builder()
                .description("a")
                .email("email@email.com")
                .password("1234567890")
                .userName("COMPANY_USERNAME")
                .name("COMPANY_NAME").build();
        company = companyRepository.saveAndFlush(company);

        // Create a job to save
        CreateJobDTO mockJob = CreateJobDTO.builder()
                .description("We are looking for a skilled Android Developer to join our mobile team.")
                .level("Mid Level")
                .benefits("Health insurance, meal voucher, home office support")
                .build();

        // Validate flow
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJSON(mockJob))
                        .header("Authorization", tokenUtils.generateToken(company.getId()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        CreateJobDTO mockJob = CreateJobDTO.builder()
                .description("We are looking for a skilled Android Developer to join our mobile team.")
                .level("Mid Level")
                .benefits("Health insurance, meal voucher, home office support")
                .build();

        // Validate flow
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJSON(mockJob))
                        .header("Authorization", tokenUtils.generateToken(UUID.randomUUID()))
        ).andExpect(result -> {
            assertInstanceOf(CompanyNotFoundException.class, result.getResolvedException());
        });
    }


}