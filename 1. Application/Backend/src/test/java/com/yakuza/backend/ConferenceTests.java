package com.yakuza.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakuza.backend.Controller.DTO.ConferenceUpdateRequestDto;
import com.yakuza.backend.Model.Conference;
import com.yakuza.backend.Model.UserModel.Chair;
import com.yakuza.backend.Repository.ConferenceRepository;
import com.yakuza.backend.Repository.TopicRepository;
import com.yakuza.backend.Repository.UserRepository;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
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
    public void getConferencesShouldReturnOkWhenAuthenticated() throws Exception {
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(conference1.getName())))
                .andExpect(jsonPath("$[1].name", is(conference2.getName())));
    }

    @Test
    public void getConferencesShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
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
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getConferenceDetailsShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        Conference conference = new Conference();
        conference.setName("conf1");
        conference.setId(1);
        var opt = Optional.of(conference);

        Mockito.when(conferenceRepository.findById(1)).thenReturn(opt);

        mvc.perform(
                MockMvcRequestBuilders.get("/conference/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"AUTHOR"})
    public void getConferenceDetailsShouldReturnOk() throws Exception {
        Conference conference = new Conference();
        conference.setName("conf1");
        conference.setId(1);
        conference.setChair(new Chair());
        conference.setTopicsOfInterest(new HashSet<>());
        var opt = Optional.of(conference);

        Mockito.when(conferenceRepository.findById(1)).thenReturn(opt);

        mvc.perform(
                MockMvcRequestBuilders.get("/conference/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(conference.getName())))
                .andExpect(jsonPath("$.id", is(conference.getId())));
    }

    @Test
    @WithMockUser(authorities = {"AUTHOR"})
    public void getConferenceDetailsShouldReturnNotFoundWhenConferenceDoesntExist() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/conference/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {"CHAIR"}, username = "radoo")
    public void updateConferenceShouldUpdateConference() throws Exception {
        Conference conference = new Conference();
        conference.setName("conf1");
        conference.setId(1);
        Chair chair = new Chair();
        chair.setId(1);
        conference.setChair(chair);

        Mockito.when(conferenceRepository.findById(1)).thenReturn(Optional.of(conference));
        Mockito.when(userRepository.findByUsername("radoo")).thenReturn(chair);

        ObjectMapper mapper = new ObjectMapper();
        var updateRequest = new ConferenceUpdateRequestDto();
        updateRequest.setName("conf_updated");

        mvc.perform(
                MockMvcRequestBuilders.put("/conference/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateRequest))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(conferenceRepository).save(ArgumentMatchers.argThat(
                c -> c.getName().equals("conf_updated")
        ));
    }

    @Test
    @WithMockUser(authorities = {"AUTHOR"})
    public void updateConferenceShouldReturnUnauthorizedWhenNotChair() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.put("/conference/1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"CHAIR"}, username = "radoo")
    public void updateConferenceShouldReturnUnauthorizedWhenNotTheChairOfTheConference() throws Exception {
        Conference conference = new Conference();
        conference.setName("conf1");
        conference.setId(1);
        Chair chair = new Chair();
        chair.setId(1);
        conference.setChair(chair);

        Chair radoo = new Chair();
        chair.setId(2);

        Mockito.when(conferenceRepository.findById(1)).thenReturn(Optional.of(conference));
        Mockito.when(userRepository.findByUsername("radoo")).thenReturn(radoo);

        ObjectMapper mapper = new ObjectMapper();
        var updateRequest = new ConferenceUpdateRequestDto();
        updateRequest.setName("conf_updated");

        mvc.perform(
                        MockMvcRequestBuilders.put("/conference/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updateRequest))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"CHAIR"}, username = "radoo")
    public void updateConferenceShouldReturnNotFoundWhenConferenceDoesntExist() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var updateRequest = new ConferenceUpdateRequestDto();
        updateRequest.setName("conf_updated");

        mvc.perform(
                        MockMvcRequestBuilders.put("/conference/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updateRequest))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
