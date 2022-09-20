package com.example.meby98.testWithMariaDB;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
public class TestCountryService {

    @Autowired
    CountryService countryService;

    @Test
    void testFindAll() {
        assertEquals(4, countryService.findAll().size());
    }

    @Test
    void testFindById() {
        CountryModel test = new CountryModel(Long.valueOf(1), "Mexico", 130497248);
        CountryModel testFindByIdService = countryService.findById(Long.valueOf(1)).get();
        assertEquals(test.getName(), testFindByIdService.getName());
        assertEquals(test.getPopulation(), testFindByIdService.getPopulation());
    }
}
