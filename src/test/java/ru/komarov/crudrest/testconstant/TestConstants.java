package ru.komarov.crudrest.testconstant;

import java.time.LocalDate;

public class TestConstants {
    public static final String PATH_OF_SQL_SCRIPT = "classpath:sql_script/testdatainit.sql";
    public static final Long ID_1 = 1L;
    public static final Long ID_2 = 2L;
    public static final Long ID_3 = 3L;
    public static final Long ID_4 = 4L;
    public static final String ENGINEER_NAME_1 = "John";
    public static final String ENGINEER_LAST_NAME_1 = "Doe";
    public static final LocalDate BIRTHDATE_1 = LocalDate.of(1990, 1, 1);
    public static final String STRING_BIRTHDATE_1 = "1990-01-01";
    public static final String ADDRESS_1 = "123 Main St";
    public static final String PHONE_NUMBER_1 = "123-456-7890";
    public static final String CONTACT_PERSON_1 = "John Smith";
    public static final LocalDate REQUEST_DATE_1 = LocalDate.of(2023, 8, 10);
    public static final String STRING_REQUEST_DATE_1 = "2023-08-10";
    public static final String ENGINEER_NAME_2 = "Jane";
    public static final String ENGINEER_LAST_NAME_2 = "Smith";
    public static final LocalDate BIRTHDATE_2 = LocalDate.of(1985, 5, 15);
    public static final String STRING_BIRTHDATE_2 = "1985-05-15";
    public static final String ADDRESS_2 = "456 Elm St";
    public static final String PHONE_NUMBER_2 = "987-654-3210";
    public static final String CONTACT_PERSON_2 = "Jane Doe";
    public static final LocalDate REQUEST_DATE_2 = LocalDate.of(2023, 8, 15);
    public static final String STRING_REQUEST_DATE_2 = "2023-08-15";
    public static final String ENGINEER_NAME_3 = "Michael";
    public static final String ENGINEER_LAST_NAME_3 = "Johnson";
    public static final LocalDate BIRTHDATE_3 = LocalDate.of(1992, 9, 30);
    public static final String STRING_BIRTHDATE_3 = "1992-09-30";
    public static final String ADDRESS_3 = "789 Oak St";
    public static final String PHONE_NUMBER_3 = "555-123-4567";
    public static final String CONTACT_PERSON_3 = "Michael Johnson";
    public static final LocalDate REQUEST_DATE_3 = LocalDate.of(2023, 8, 20);
    public static final String STRING_REQUEST_DATE_3 = "2023-08-20";
    public static final String ENGINEER_NAME_4 = "Snoop";
    public static final String ENGINEER_LAST_NAME_4 = "Dogg";
    public static final LocalDate BIRTHDATE_4 = LocalDate.of(1971, 10, 20);
    public static final String EXPECTED_JSON_ENGINEER = "{\"id\":1,\"name\":\"John\",\"lastName\":\"Doe\"," +
            "\"birthdate\":\"1990-01-01\",\"carAvailability\":true," +
            "\"requests\":[{\"id\":1,\"address\":\"123 Main St\"," +
            "\"contactPerson\":\"John Smith\",\"phoneNumber\":\"123-456-7890\"," +
            "\"requestDate\":\"2023-08-10\",\"engineerId\":1}]}\n";
    public static final String EXPECTED_JSON1 = "{\"name\" : \"Danila\", \"lastName\" : \"Bagrov\"," +
            " \"birthdate\" : \"1975-08-05\", \"carAvailability\" : false}";
    public static final String EXPECTED_JSON2 = "[\"Last name should not be empty\"," +
            "\"The engineer must be under 65 years of age\"," +
            "\"The engineer must be over 18 years old\"," +
            "\"Name should not be empty\"," +
            "\"The presence of a car must be indicated\"]";


    public static final String EXPECTED_JSON_CREATED = "{\"result\":\"Engineer created\"}";
    public static final String EXPECTED_JSON_DELETED = "{\"result\":\"id 1 deleted\"}";
    public static final String EXPECTED_JSON_UPDATED = "{\"result\":\"Engineer updated\"}";
    public static final String EMPTY_JSON = "{}";
    public static final String ENDPOINT_ENGINEERS = "/engineers";
    public static final String ENDPOINT_ENGINEERS_REQUESTS = "/engineers/requests";
    public static final String ENDPOINT_ENGINEERS_ID = "/engineers/{id}";
    public static final String WAS_NOT_FOUND_BY_ID_4 = "Engineer was not found by id: 4";
}
