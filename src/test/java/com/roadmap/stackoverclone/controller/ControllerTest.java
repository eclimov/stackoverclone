package com.roadmap.stackoverclone.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.roadmap.stackoverclone.constant.ProfileConstants;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc(addFilters = false)
// @ContextConfiguration(classes = SecurityConfig.class)
//@WebAppConfiguration
@ActiveProfiles(ProfileConstants.TEST)
// @Sql({"/db/data/fixtures.mysql.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    protected static String accessTokenAdmin;

    @Mock
    protected Authentication mockAauthentication;

    @Mock
    protected SecurityContext mockSecurityContext;

    @BeforeEach
    public void setUp() throws Exception {
        if (accessTokenAdmin == null) {
            accessTokenAdmin = obtainAccessToken("admin.admin", "jwtpass");
        }
    }

    protected void mockSecurityAsUser (String username) {
        Mockito.when(mockSecurityContext.getAuthentication()).thenReturn(mockAauthentication);
        Mockito.when(mockAauthentication.getName()).thenReturn(username);
        SecurityContextHolder.setContext(mockSecurityContext);
    }

    protected  <ObjectDataType> String generateJson(ObjectDataType objectData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(objectData);
    }

    // https://www.baeldung.com/oauth-api-testing-with-spring-mvc
    private String obtainAccessToken(String username, String password) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "jwtclientid");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic("jwtclientid","XY7kmzoNzl100"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public abstract void create() throws Exception;

    @Test
    public abstract void update() throws Exception;

    @Test
    public abstract void delete() throws Exception;

    @Test
    public abstract void deleteNonexistent() throws Exception;

    @Test
    public abstract void find() throws Exception;

    @Test
    public abstract void findNonexistent() throws Exception;

    @Test
    public abstract void get() throws Exception;
}
