package com.example.meby98.testWithMariaDB;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CountryRestController.class)
@TestPropertySource(locations = "classpath:db-test.properties")
public class TestCountryController {

        @MockBean
        private CountryService countryService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        // Verifying HTTP Request Matching
        @Test
        void testGetAllCountries() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/api/country").contentType("application/json"))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        // Verifying Inout Deserialization
        @Test
        void testGetById() throws Exception {
                when(countryService.findById(1L))
                                .thenReturn(Optional.of(new CountryModel(1L, "MarcosCountry", 1000000)));
                mockMvc.perform(MockMvcRequestBuilders.get("/api/country/{id}", 1).contentType("application/json"))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk());
                mockMvc.perform(MockMvcRequestBuilders.get("/api/country/{id}", "asdads")
                                .contentType("application/json"))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        // Bean validation
        @Test
        void testCreateCountry() throws JsonProcessingException, Exception {
                when(countryService.create(any(CountryRequest.class))).thenReturn(1L);
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/country")
                                                .contentType("application/json")
                                                .content(objectMapper.writeValueAsString(
                                                                new CountryRequest("CountryMarcos", 1000000))))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isCreated());
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/country")
                                                .contentType("application/json")
                                                .content(objectMapper.writeValueAsString(
                                                                new CountryRequest(null, 1000000))))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/country")
                                                .contentType("application/json")
                                                .content(objectMapper.writeValueAsString(
                                                                new CountryRequest("", 1000000))))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.content().string("Arguments not valids"));
        }
}
