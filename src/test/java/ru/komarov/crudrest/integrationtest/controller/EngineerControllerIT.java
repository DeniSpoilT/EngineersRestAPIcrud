package ru.komarov.crudrest.integrationtest.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.komarov.crudrest.testconstant.TestConstants.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EngineerControllerIT {

    private MockMvc mockMvc;
    private JdbcTemplate jdbcTemplate;
    private ResourceLoader resourceLoader;

    @Autowired
    public EngineerControllerIT(MockMvc mockMvc, JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.mockMvc = mockMvc;
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    @BeforeAll
    public void setup() {
        Resource resource = resourceLoader.getResource(PATH_OF_SQL_SCRIPT);
        try {
            String script = new String(Files.readAllBytes(resource.getFile().toPath()));
            jdbcTemplate.execute(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void findAll_ShouldReturnJsonWithListOfAllEngineers_IT() {
        mockMvc.perform(get(ENDPOINT_ENGINEERS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ID_1))
                .andExpect(jsonPath("$[0].name").value(ENGINEER_NAME_1))
                .andExpect(jsonPath("$[0].lastName").value(ENGINEER_LAST_NAME_1))
                .andExpect(jsonPath("$[0].birthdate").value(STRING_BIRTHDATE_1))
                .andExpect(jsonPath("$[0].carAvailability").value(TRUE))
                .andExpect(jsonPath("$[0].requests").doesNotExist())
                .andExpect(jsonPath("$[1].id").value(ID_2))
                .andExpect(jsonPath("$[1].name").value(ENGINEER_NAME_2))
                .andExpect(jsonPath("$[1].lastName").value(ENGINEER_LAST_NAME_2))
                .andExpect(jsonPath("$[1].birthdate").value(STRING_BIRTHDATE_2))
                .andExpect(jsonPath("$[1].carAvailability").value(FALSE))
                .andExpect(jsonPath("$[1].requests").doesNotExist())
                .andExpect(jsonPath("$[2].id").value(ID_3))
                .andExpect(jsonPath("$[2].name").value(ENGINEER_NAME_3))
                .andExpect(jsonPath("$[2].lastName").value(ENGINEER_LAST_NAME_3))
                .andExpect(jsonPath("$[2].birthdate").value(STRING_BIRTHDATE_3))
                .andExpect(jsonPath("$[2].carAvailability").value(TRUE))
                .andExpect(jsonPath("$[2].requests").doesNotExist());
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void findAllEngineersWithRequest_ShouldReturnJsonWithListOfAllEngineersAndTheirRequests_IT() {
        mockMvc.perform(get(ENDPOINT_ENGINEERS_REQUESTS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ID_1))
                .andExpect(jsonPath("$[0].name").value(ENGINEER_NAME_1))
                .andExpect(jsonPath("$[0].lastName").value(ENGINEER_LAST_NAME_1))
                .andExpect(jsonPath("$[0].birthdate").value(STRING_BIRTHDATE_1))
                .andExpect(jsonPath("$[0].carAvailability").value(TRUE))
                .andExpect(jsonPath("$[0].requests[0].id").value(ID_1))
                .andExpect(jsonPath("$[0].requests[0].address").value(ADDRESS_1))
                .andExpect(jsonPath("$[0].requests[0].contactPerson").value(CONTACT_PERSON_1))
                .andExpect(jsonPath("$[0].requests[0].phoneNumber").value(PHONE_NUMBER_1))
                .andExpect(jsonPath("$[0].requests[0].requestDate").value(STRING_REQUEST_DATE_1))
                .andExpect(jsonPath("$[0].requests[0].engineerId").value(ID_1))
                .andExpect(jsonPath("$[1].id").value(ID_2))
                .andExpect(jsonPath("$[1].name").value(ENGINEER_NAME_2))
                .andExpect(jsonPath("$[1].lastName").value(ENGINEER_LAST_NAME_2))
                .andExpect(jsonPath("$[1].birthdate").value(STRING_BIRTHDATE_2))
                .andExpect(jsonPath("$[1].carAvailability").value(FALSE))
                .andExpect(jsonPath("$[1].requests[0].id").value(ID_2))
                .andExpect(jsonPath("$[1].requests[0].address").value(ADDRESS_2))
                .andExpect(jsonPath("$[1].requests[0].contactPerson").value(CONTACT_PERSON_2))
                .andExpect(jsonPath("$[1].requests[0].phoneNumber").value(PHONE_NUMBER_2))
                .andExpect(jsonPath("$[1].requests[0].requestDate").value(STRING_REQUEST_DATE_2))
                .andExpect(jsonPath("$[1].requests[0].engineerId").value(ID_2))
                .andExpect(jsonPath("$[2].id").value(ID_3))
                .andExpect(jsonPath("$[2].name").value(ENGINEER_NAME_3))
                .andExpect(jsonPath("$[2].lastName").value(ENGINEER_LAST_NAME_3))
                .andExpect(jsonPath("$[2].birthdate").value(STRING_BIRTHDATE_3))
                .andExpect(jsonPath("$[2].carAvailability").value(TRUE))
                .andExpect(jsonPath("$[2].requests[0].id").value(ID_3))
                .andExpect(jsonPath("$[2].requests[0].address").value(ADDRESS_3))
                .andExpect(jsonPath("$[2].requests[0].contactPerson").value(CONTACT_PERSON_3))
                .andExpect(jsonPath("$[2].requests[0].phoneNumber").value(PHONE_NUMBER_3))
                .andExpect(jsonPath("$[2].requests[0].requestDate").value(STRING_REQUEST_DATE_3))
                .andExpect(jsonPath("$[2].requests[0].engineerId").value(ID_3));
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void create_ShouldReturnBadRequestAndJsonWithArrayOfErrorsIfPassedInvalidData_IT() {
        mockMvc.perform(post(ENDPOINT_ENGINEERS)
                        .content(EMPTY_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(EXPECTED_JSON2));
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void create_ShouldReturnIsCreatedAndMessageIfPassedValidData_IT() {
        mockMvc.perform(post(ENDPOINT_ENGINEERS)
                        .content(EXPECTED_JSON1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(EXPECTED_JSON_CREATED));
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void delete_ShouldReturnOkAndMessageIfPassedValidId_IT() {
        mockMvc.perform(delete(ENDPOINT_ENGINEERS_ID, ID_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(EXPECTED_JSON_DELETED));
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void update_ShouldReturnOkIfPassedValidIdAndDTO(){
        mockMvc.perform(put(ENDPOINT_ENGINEERS_ID, ID_1)
                .content(EXPECTED_JSON1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(EXPECTED_JSON_UPDATED));

    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void update_ShouldReturnNotFoundIfPassedInvalidId(){
        mockMvc.perform(put(ENDPOINT_ENGINEERS_ID, ID_4)
                        .content(EXPECTED_JSON1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(WAS_NOT_FOUND_BY_ID_4));

    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void update_ShouldReturnBadRequestAndJsonWithArrayOfErrorsIfPassedInvalidData_IT() {
        mockMvc.perform(put(ENDPOINT_ENGINEERS_ID, ID_1)
                        .content(EMPTY_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(EXPECTED_JSON2));
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void findById_ShouldReturnJsonEngineerWithSpecifiedId_IT(){
        mockMvc.perform(get(ENDPOINT_ENGINEERS_ID, ID_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(EXPECTED_JSON_ENGINEER));
    }

    @Test
    @Rollback
    @SneakyThrows
    @Transactional
    public void findById_ShouldReturnNotFoundIfPassedMissingId_IT(){
        mockMvc.perform(get(ENDPOINT_ENGINEERS_ID, ID_4))
                .andExpect(status().isNotFound())
                .andExpect(content().string(WAS_NOT_FOUND_BY_ID_4));
    }



}
