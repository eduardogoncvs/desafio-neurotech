package gateway.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class AddressControllerTest {

    private final String URL = "/api/cep/v1";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 01 - Should return a address when a valid CEP is given.")
    void shouldReturnAddress() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL + "/51011070")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @DisplayName("Test 02 - Should return an error when an invalid CEP is given")
    void shouldReturnAnErrorWhenAnInvalidCEPIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL + "/")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));

    }

    @Test
    @DisplayName("Test 03 - Should return an error when an invalid type CEP is given")
    void shouldReturnAnErrorWhenAnInvalidTypeCEPIsGiven() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(new URI(URL + "/5151515151")))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

    }
}
