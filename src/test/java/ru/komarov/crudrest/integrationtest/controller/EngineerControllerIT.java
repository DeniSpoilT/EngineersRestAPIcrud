package ru.komarov.crudrest.integrationtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static ru.komarov.crudrest.testconstant.TestConstants.PATH_OF_SQL_SCRIPT;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@Sql(scripts = PATH_OF_SQL_SCRIPT)
public class EngineerControllerIT {
    @Autowired
    MockMvc mock;


}
