package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.AnswerData;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AnswerControllerTest extends ControllerTest {
    private String baseUrl = "/answers";

    private Long idQuestionExisting = 1L;

    private Long idExisting = 1L;
    private Long idNonexisting = 44L;

    public AnswerData createData() {
        return new AnswerData()
                .setText("my new text?")
                ;
    }

    @Override
    public void _create() throws Exception {
        AnswerData data = this.createData();
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
    public void _update() throws Exception {
        AnswerData data = this.createData();
        String json = this.generateJson(data);

        mockMvc
                .perform(get(baseUrl + "/{id}", idExisting))
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
                .perform(get(baseUrl + "/{id}", idExisting))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(idExisting.intValue())))
                .andExpect(jsonPath("$.text", is(data.getText())))
        ;
    }

    @Override
    public void _delete() throws Exception {
        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(delete(baseUrl + "/{id}", idExisting))
                .andExpect(status().isOk())
        ;

        mockMvc
                .perform(get(baseUrl + "/{id}", idExisting))
                .andExpect(status().isNotFound())
        ;
    }

    @Override
    public void _deleteNonexistent() throws Exception {
        mockSecurityAsUser("admin.admin");
        mockMvc
                .perform(delete(baseUrl + "/{id}", idNonexisting))
                .andExpect(status().isNotFound())
        ;
    }

    @Override
    public void _find() throws Exception {
        mockMvc
                .perform(get(baseUrl + "/{id}", idExisting))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(idExisting.intValue())))
                .andExpect(jsonPath("$.text", is("answer for question 1 user 2")))
        ;
    }

    @Override
    public void _findNonexistent() throws Exception {
        mockMvc
                .perform(get(baseUrl + "/{id}", idNonexisting))
                .andExpect(status().isNotFound())
        ;
    }

    @Override
    public void _get() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(baseUrl + "/question/{questionId}", idQuestionExisting))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                ;
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void _voteUp() throws Exception {
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
    public void _voteDown() throws Exception {
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
