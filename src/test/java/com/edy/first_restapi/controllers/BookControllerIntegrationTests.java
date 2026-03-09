package com.edy.first_restapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.edy.first_restapi.TestDataUtil;
import com.edy.first_restapi.domain.entities.AuthorEntity;
import com.edy.first_restapi.domain.entities.BookEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsHttp201Created() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
        
        
        BookEntity testBookEntityA = TestDataUtil.createTestBookEntityA(testAuthorA);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/books/" + testBookEntityA.getIsbn())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(testBookEntityA))
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );

    }


}
