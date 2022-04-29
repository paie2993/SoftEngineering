package com.yakuza.backend;

import com.yakuza.backend.Model.Conference;
import com.yakuza.backend.Repository.ConferenceRepository;
import com.yakuza.backend.Repository.TopicRepository;
import com.yakuza.backend.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.internal.matchers.text.ValuePrinter.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class ConferenceTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ConferenceRepository conferenceRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TopicRepository topicRepository;


    @Test
    public void contextLoads() {

    }

    @Test
    @WithMockUser(authorities = {"AUTHOR"})
    public void getConferencesTest() throws Exception {
        Conference conference1 = new Conference();
        conference1.setName("conf1");
        Conference conference2 = new Conference();
        conference1.setName("conf2");

        List<Conference> allConferences = Arrays.asList(conference1, conference2);

        Mockito.when(conferenceRepository.findAll()).thenReturn(allConferences);

        mvc.perform(
                MockMvcRequestBuilders.get("/conference/")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(conference1.getName())))
                .andExpect(jsonPath("$[1].name", is(conference2.getName())));
    }
}
