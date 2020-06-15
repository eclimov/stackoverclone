package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.TextData;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AnswerControllerTest extends ControllerTest {
    private String baseUrl = "/answers";

    private Long idQuestionExisting = 1L;

    private Long idExisting = 1L;
    private Long idNonexisting = 44L;

    public TextData createData() {
        return new TextData()
                .setText("my new text?")
                ;
    }

    @Override
    public void create() throws Exception {
        TextData data = this.createData();
        String json = this.generateJson(data);

        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(
                        post(baseUrl + "/question/{questionId}", idQuestionExisting)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.text").value(data.getText()))
        ;
    }

    @Override
    public void update() throws Exception {
        TextData data = this.createData();
        String json = this.generateJson(data);

        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + "/{id}", idExisting))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(idExisting.intValue())))
                .andExpect(jsonPath("$.text", is(not(data.getText()))))
        ;

        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(
                        put(baseUrl + "/{id}", idExisting)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.text").value(data.getText()))
        ;

        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + "/{id}", idExisting))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(idExisting.intValue())))
                .andExpect(jsonPath("$.text", is(data.getText())))
        ;
    }

    @Override
    public void delete() throws Exception {
        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(MockMvcRequestBuilders.delete(baseUrl + "/{id}", idExisting))
                .andExpect(status().isOk())
        ;

        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + "/{id}", idExisting))
                .andExpect(status().isNotFound())
        ;
    }

    @Override
    public void deleteNonexistent() throws Exception {
        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(MockMvcRequestBuilders.delete(baseUrl + "/{id}", idNonexisting))
                .andExpect(status().isNotFound())
        ;
    }

    @Override
    public void find() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + "/{id}", idExisting))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(idExisting.intValue())))
                .andExpect(jsonPath("$.text", is("answer for question 1 user 2")))
        ;
    }

    @Override
    public void findNonexistent() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + "/{id}", idNonexisting))
                .andExpect(status().isNotFound())
        ;
    }

    @Override
    public void get() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get(baseUrl + "/question/{questionId}", idQuestionExisting))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                ;
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void voteUp() throws Exception {
        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(
                        put(baseUrl + "/{id}/voteup", idExisting)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void voteDown() throws Exception {
        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(
                        put(baseUrl + "/{id}/votedown", idExisting)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
        ;
    }
}
