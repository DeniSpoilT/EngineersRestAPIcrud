package ru.komarov.crudrest.integrationtest.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.service.impl.EngineerServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static ru.komarov.crudrest.testconstant.TestConstants.PATH_OF_SQL_SCRIPT;

@SpringBootTest
@Sql(scripts = PATH_OF_SQL_SCRIPT)
public class EngineerServiceImplIT {

    @Autowired
    private EngineerServiceImpl engineerService;
    private EngineerDTO expectedEngineer;

    @BeforeEach
    void init() {
        expectedEngineer = EngineerDTO.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .carAvailability(true)
                .birthdate(LocalDate.of(1990, 1, 1))
                .requests(List.of(RequestOnRepairDTO.builder()
                        .id(1L)
                        .address("123 Main St")
                        .phoneNumber("123-456-7890")
                        .contactPerson("John Smith")
                        .requestDate(LocalDate.of(2023, 8, 10))
                        .engineerId(1L)
                        .build()))
                .build();
    }

    @Test
    @Transactional
    @DirtiesContext
    void findByIdShould_returnEngineerDTOEqualToPassedIdFromDatabase_IT() {

        EngineerDTO engineerDTObyId = engineerService.findById(1L);

        Assertions.assertEquals(expectedEngineer, engineerDTObyId);
    }

    @Test
    @Transactional
    @DirtiesContext
    void findByIdShould_throwExceptionWithIdNotFound_IT() {

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> engineerService.findById(10L));

        Assertions.assertEquals(HttpStatus.NOT_FOUND + " \"id: 10 not found\"", exception.getMessage());
    }

    @Test
    @Transactional
    @DirtiesContext
    void updateShould_updateFieldsInDatabaseToSpecificId_IT() {
        expectedEngineer.setName("Johny");
        expectedEngineer.setLastName("Smith");
        expectedEngineer.setBirthdate(LocalDate.of(1995, 5, 5));
        expectedEngineer.setCarAvailability(false);
        engineerService.update(1L, expectedEngineer);

        EngineerDTO actualEngineer = engineerService.findById(1L);
        Assertions.assertEquals(expectedEngineer, actualEngineer);
    }

    @Test
    @Transactional
    @DirtiesContext
    void updateShould_throwExceptionWithIdNotFound_IT() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> engineerService.update(10L, expectedEngineer));

        Assertions.assertEquals(HttpStatus.NOT_FOUND + " \"id: 10 not found\"", exception.getMessage());
    }

    @Test
    @Transactional
    @DirtiesContext
    void findAllShould_returnListOfAllEngineersFromDB_IT() {
        List<EngineerDTO> expectedList = List.of(
                EngineerDTO.builder()
                        .id(1L)
                        .name("John")
                        .lastName("Doe")
                        .carAvailability(true)
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .build(),
                EngineerDTO.builder()
                        .id(2L)
                        .name("Jane")
                        .lastName("Smith")
                        .carAvailability(false)
                        .birthdate(LocalDate.of(1985, 5, 15))
                        .build(),
                EngineerDTO.builder()
                        .id(3L)
                        .name("Michael")
                        .lastName("Johnson")
                        .carAvailability(true)
                        .birthdate(LocalDate.of(1992, 9, 30))
                        .build());

        List<EngineerDTO> actualList = engineerService.findAll();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @Transactional
    @DirtiesContext
    void findAllEngineersWithRequestsShould_returnListOfAllEngineersWithRequestsFromDB_IT() {
        List<EngineerDTO> expectedList = List.of(
                EngineerDTO.builder()
                        .id(1L)
                        .name("John")
                        .lastName("Doe")
                        .carAvailability(true)
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .requests(List.of(RequestOnRepairDTO.builder()
                                .id(1L)
                                .address("123 Main St")
                                .phoneNumber("123-456-7890")
                                .contactPerson("John Smith")
                                .requestDate(LocalDate.of(2023, 8, 10))
                                .engineerId(1L)
                                .build()))
                        .build(),
                EngineerDTO.builder()
                        .id(2L)
                        .name("Jane")
                        .lastName("Smith")
                        .carAvailability(false)
                        .birthdate(LocalDate.of(1985, 5, 15))
                        .requests(List.of(RequestOnRepairDTO.builder()
                                .id(2L)
                                .address("456 Elm St")
                                .phoneNumber("987-654-3210")
                                .contactPerson("Jane Doe")
                                .requestDate(LocalDate.of(2023, 8, 15))
                                .engineerId(2L)
                                .build()))
                        .build(),
                EngineerDTO.builder()
                        .id(3L)
                        .name("Michael")
                        .lastName("Johnson")
                        .carAvailability(true)
                        .birthdate(LocalDate.of(1992, 9, 30))
                        .requests(List.of(RequestOnRepairDTO.builder()
                                .id(3L)
                                .address("789 Oak St")
                                .phoneNumber("555-123-4567")
                                .contactPerson("Michael Johnson")
                                .requestDate(LocalDate.of(2023, 8, 20))
                                .engineerId(3L)
                                .build()))
                        .build());
        List<EngineerDTO> actualList = engineerService.findAllEngineersWithRequests();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @Transactional
    @DirtiesContext
    void deleteByIdShould_deleteEngineerFromDBWithPassedId_IT() {
        Assertions.assertNotNull(engineerService.findById(1L));

        engineerService.deleteById(1L);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> engineerService.findById(1L));
        Assertions.assertEquals(HttpStatus.NOT_FOUND + " \"id: 1 not found\"", exception.getMessage());
    }

    @Test
    @Transactional
    @DirtiesContext
    void deleteByIdShould_throwNotFoundExceptionIfPassedIdNotFound_IT() {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> engineerService.deleteById(10L));

        Assertions.assertEquals(HttpStatus.NOT_FOUND + " \"id: 10 not found\"", exception.getMessage());
    }


    @Test
    @Transactional
    @DirtiesContext
    void createShould_addNewEngineerToDBEqualDTO_IT() {
        EngineerDTO expectedEngineerDto = EngineerDTO.builder()
                .id(4L)
                .name("Snoop")
                .lastName("Dogg")
                .carAvailability(true)
                .birthdate(LocalDate.of(1971, 10, 20))
                .build();
        engineerService.create(expectedEngineerDto);
        EngineerDTO actualEngineerDto = engineerService.findById(4L);
        Assertions.assertEquals(expectedEngineerDto, actualEngineerDto);
    }
}
