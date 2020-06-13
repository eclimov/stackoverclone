package com.roadmap.stackoverclone.controller;

import com.roadmap.stackoverclone.model.data.UserData;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest  extends ControllerTest {
    private String baseUrl = "/users";
    private Long idExisting = 1L;
    private Long idNonexisting = 44L;

    public UserData createData() {
        return new UserData()
                .setUsername("test.name")
                ;
    }

    @Override
    public void _create() throws Exception {
        UserData data = this.createData();
        String json = this.generateJson(data);

        mockSecurityAsUser("admin.admin");
        ResultActions resultActions = mockMvc
                .perform(
                        post(baseUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(IsNull.notNullValue()))
                .andExpect(jsonPath("$.name").value(data.getUsername()))
        ;

        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Override
    public void _update() throws Exception {
        UserData data = this.createData();
        String json = this.generateJson(data);

        mockMvc
                .perform(get(baseUrl + "/{id}", idExisting))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(idExisting.intValue())))
                .andExpect(jsonPath("$.name", is(not(data.getUsername()))))
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
                .andExpect(jsonPath("$.name").value(data.getUsername()))
        ;

        mockMvc
                .perform(get(baseUrl + "/{id}", idExisting))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(idExisting.intValue())))
                .andExpect(jsonPath("$.name", is(data.getUsername())))
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
                .andExpect(jsonPath("$.name", is("john.doe")))
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
                .perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                ;
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void _getStats() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(baseUrl + "/{id}/stats", idExisting))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.answersCount", is(0)))
                .andExpect(jsonPath("$.questionsCount", is(2)))
                .andExpect(jsonPath("$.rating", is(2)))
        ;

        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }
}
