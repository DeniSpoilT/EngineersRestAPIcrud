package ru.komarov.crudrest.integrationtest.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.service.impl.EngineerServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static ru.komarov.crudrest.constant.Constant.BY_ID;
import static ru.komarov.crudrest.constant.Constant.ENGINEER_NOT_FOUND;
import static ru.komarov.crudrest.testconstant.TestConstants.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EngineerServiceImplIT {

    private EngineerServiceImpl engineerService;
    private JdbcTemplate jdbcTemplate;
    private ResourceLoader resourceLoader;

    @Autowired
    public EngineerServiceImplIT(EngineerServiceImpl engineerService,
                                 JdbcTemplate jdbcTemplate,
                                 ResourceLoader resourceLoader) {
        this.engineerService = engineerService;
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
    void findById_ShouldReturnEngineerDTOEqualToPassedIdFromDatabase_IT() {

        EngineerDTO engineerDTObyId = engineerService.findById(1L);
        EngineerDTO expectedEngineer = getExpectedEngineer1();

        Assertions.assertEquals(expectedEngineer, engineerDTObyId);
    }

    @Test
    void findById_ShouldThrowExceptionWithIdNotFound_IT() {

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> engineerService.findById(ID_4));

        Assertions.assertEquals(ENGINEER_NOT_FOUND + BY_ID + ID_4, exception.getMessage());
    }

    @Test
    @Rollback
    @Transactional
    void update_ShouldUpdateFieldsInDatabaseToSpecificId_IT() {
        EngineerDTO expectedEngineer = getExpectedEngineer1();
        expectedEngineer.setName(ENGINEER_NAME_2);
        expectedEngineer.setLastName(ENGINEER_LAST_NAME_2);
        expectedEngineer.setBirthdate(BIRTHDATE_2);
        expectedEngineer.setCarAvailability(FALSE);
        engineerService.update(ID_1, expectedEngineer);

        EngineerDTO actualEngineer = engineerService.findById(1L);
        Assertions.assertEquals(expectedEngineer, actualEngineer);
    }

    @Test
    @Rollback
    @Transactional
    void update_ShouldThrowExceptionWithIdNotFound_IT() {
        EngineerDTO expectedEngineer = getExpectedEngineer1();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> engineerService.update(ID_4, expectedEngineer));

        Assertions.assertEquals(ENGINEER_NOT_FOUND + BY_ID + ID_4, exception.getMessage());
    }

    @Test
    void findAll_ShouldReturnListOfAllEngineersFromDB_IT() {
        List<EngineerDTO> expectedList = getExpectedEngineerList();

        List<EngineerDTO> actualList = engineerService.findAll();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void findAllEngineersWithRequests_ShouldReturnListOfAllEngineersWithRequestsFromDB_IT() {
        List<EngineerDTO> expectedList = getExpectedEngineerList2();
        List<EngineerDTO> actualList = engineerService.findAllEngineersWithRequests();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @Rollback
    @Transactional
    void deleteById_ShouldDeleteEngineerFromDBWithPassedId_IT() {
        Assertions.assertNotNull(engineerService.findById(ID_1));

        engineerService.deleteById(ID_1);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> engineerService.findById(ID_1));
        Assertions.assertEquals(ENGINEER_NOT_FOUND + BY_ID + ID_1, exception.getMessage());
    }

    @Test
    void deleteById_ShouldThrowNotFoundExceptionIfPassedIdNotFound_IT() {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> engineerService.deleteById(ID_4));

        Assertions.assertEquals(ENGINEER_NOT_FOUND + BY_ID + ID_4, exception.getMessage());
    }

    @Test
    @Rollback
    @Transactional
    void create_ShouldAddNewEngineerToDBEqualDTO_IT() {
        EngineerDTO expectedEngineer4 = getExpectedEngineer2();
        engineerService.create(expectedEngineer4);
        EngineerDTO actualEngineerDto = engineerService.findById(4L);
        Assertions.assertEquals(expectedEngineer4, actualEngineerDto);
    }

    private EngineerDTO getExpectedEngineer1() {
        EngineerDTO dto = getEngineerDTO1();
        dto.setRequests(List.of(getRequestOnRepairDTO1()));
        return dto;
    }

    private EngineerDTO getExpectedEngineer2() {
        return EngineerDTO.builder()
                .id(ID_4)
                .name(ENGINEER_NAME_4)
                .lastName(ENGINEER_LAST_NAME_4)
                .carAvailability(TRUE)
                .birthdate(BIRTHDATE_4)
                .build();
    }

    private List<EngineerDTO> getExpectedEngineerList() {
        return List.of(getEngineerDTO1(), getEngineerDTO2(), getEngineerDTO3());
    }

    private List<EngineerDTO> getExpectedEngineerList2() {
        EngineerDTO dto1 = getEngineerDTO1();
        dto1.setRequests(List.of(getRequestOnRepairDTO1()));
        EngineerDTO dto2 = getEngineerDTO2();
        dto2.setRequests(List.of(getRequestOnRepairDTO2()));
        EngineerDTO dto3 = getEngineerDTO3();
        dto3.setRequests(List.of(getRequestOnRepairDTO3()));

        return List.of(dto1, dto2, dto3);
    }

    private EngineerDTO getEngineerDTO1() {
        return EngineerDTO.builder()
                .id(ID_1)
                .name(ENGINEER_NAME_1)
                .lastName(ENGINEER_LAST_NAME_1)
                .carAvailability(TRUE)
                .birthdate(BIRTHDATE_1)
                .build();
    }

    private EngineerDTO getEngineerDTO2() {
        return EngineerDTO.builder()
                .id(ID_2)
                .name(ENGINEER_NAME_2)
                .lastName(ENGINEER_LAST_NAME_2)
                .carAvailability(FALSE)
                .birthdate(BIRTHDATE_2)
                .build();
    }

    private EngineerDTO getEngineerDTO3() {
        return EngineerDTO.builder()
                .id(ID_3)
                .name(ENGINEER_NAME_3)
                .lastName(ENGINEER_LAST_NAME_3)
                .carAvailability(TRUE)
                .birthdate(BIRTHDATE_3)
                .build();

    }

    private RequestOnRepairDTO getRequestOnRepairDTO1() {
        return RequestOnRepairDTO.builder()
                .id(ID_1)
                .address(ADDRESS_1)
                .phoneNumber(PHONE_NUMBER_1)
                .contactPerson(CONTACT_PERSON_1)
                .requestDate(REQUEST_DATE_1)
                .engineerId(ID_1)
                .build();
    }

    private RequestOnRepairDTO getRequestOnRepairDTO2() {
        return RequestOnRepairDTO.builder()
                .id(ID_2)
                .address(ADDRESS_2)
                .phoneNumber(PHONE_NUMBER_2)
                .contactPerson(CONTACT_PERSON_2)
                .requestDate(REQUEST_DATE_2)
                .engineerId(ID_2)
                .build();
    }

    private RequestOnRepairDTO getRequestOnRepairDTO3() {
        return RequestOnRepairDTO.builder()
                .id(ID_3)
                .address(ADDRESS_3)
                .phoneNumber(PHONE_NUMBER_3)
                .contactPerson(CONTACT_PERSON_3)
                .requestDate(REQUEST_DATE_3)
                .engineerId(ID_3)
                .build();
    }
}
