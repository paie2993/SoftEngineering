package com.yakuza.backend.Controller;

import com.yakuza.backend.Controller.DTO.ConferenceInfoResponseDto;
import com.yakuza.backend.Controller.DTO.ConferenceListItemDto;
import com.yakuza.backend.Controller.DTO.ConferenceUpdateRequestDto;
import com.yakuza.backend.Model.Conference;
import com.yakuza.backend.Model.TopicOfInterest;
import com.yakuza.backend.Model.UserModel.CMSUser;
import com.yakuza.backend.Repository.ConferenceRepository;
import com.yakuza.backend.Repository.TopicRepository;
import com.yakuza.backend.Repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/conference")
public class ConferenceController {
    private final ConferenceRepository conferenceRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public ConferenceController(ConferenceRepository conferenceRepository, UserRepository userRepository, TopicRepository topicRepository) {
        this.conferenceRepository = conferenceRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @ApiOperation(value = "Get all conferences")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @GetMapping("/")
    public ResponseEntity<?> getConferences() {
        // get all the conferences and map them to DTOs
        var conferences = conferenceRepository.findAll()
                .stream()
                .map(conference -> new ConferenceListItemDto(conference.getId(), conference.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(conferences);
    }

    @ApiOperation(value = "Get details about a specific conference")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Conference was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getConferenceDetails(@PathVariable Integer id) {
        // get the conference optional from the repo
        var conferenceOptional = conferenceRepository.findById(id);

        // if the conference is not present, return 404
        if(conferenceOptional.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }

        // return the conference DTO
        Conference conference = conferenceOptional.get();
        ConferenceInfoResponseDto info = new ConferenceInfoResponseDto(conference);

        return ResponseEntity.ok(info);
    }

    @ApiOperation(value = "Update the details of a conference")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateConferenceDetails(@ApiIgnore Principal principal, @PathVariable Integer id, @RequestBody ConferenceUpdateRequestDto updateDto) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(id);
        CMSUser user = userRepository.findByUsername(principal.getName());

        // if the conference wasn't found, return 404
        if(conferenceOptional.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }

        // if the user isn't the chair of the conference, return unauthorized
        Conference conference = conferenceOptional.get();

        if(conference.getChair().getId() != user.getId()) {
            return new ResponseEntity<>("You are not the chair of this conference", HttpStatus.UNAUTHORIZED);
        }

        // update the conference

        if(updateDto.getTopics() != null) {
            Set<TopicOfInterest> topicOfInterestSet = new HashSet<>();

            // go over all the topic descriptions provided
            for(String topicDesc: updateDto.getTopics()) {
                var topicOptional = topicRepository.findByDescription(topicDesc);

                if(topicOptional.isPresent()) {
                    // if the topic already exists in the database, add it to the conference
                    TopicOfInterest topicOfInterest = topicOptional.get();
                    topicOfInterestSet.add(topicOfInterest);
                } else {
                    // otherwise, add it to the database
                    TopicOfInterest topicOfInterest = new TopicOfInterest();
                    topicOfInterest.setDescription(topicDesc);
                    topicRepository.save(topicOfInterest);
                    topicOfInterestSet.add(topicOfInterest);
                }
            }

            conference.setTopicsOfInterest(topicOfInterestSet);
        }

        // upload the other fields, if provided

        if(updateDto.getName() != null) {
            conference.setName(updateDto.getName());
        }

        if(updateDto.getURL() != null) {
            conference.setURL(updateDto.getURL());
        }

        if(updateDto.getOrganizerEmail() != null) {
            conference.setOrganizerEmail(updateDto.getOrganizerEmail());
        }

        if(updateDto.getOrganizerName() != null) {
            conference.setOrganizerName(updateDto.getOrganizerName());
        }

        if(updateDto.getOrganizerPhoneNumber() != null) {
            conference.setOrganizerPhoneNumber(updateDto.getOrganizerPhoneNumber());
        }

        if(updateDto.getPaperSubmissionDeadline() != null) {
            conference.setPaperSubmissionDeadline(updateDto.getPaperSubmissionDeadline());
        }

        if(updateDto.getPaperReviewDeadline() != null) {
            conference.setPaperReviewDeadline(updateDto.getPaperReviewDeadline());
        }

        if(updateDto.getAcceptanceNotificationDeadline() != null) {
            conference.setAcceptanceNotificationDeadline(updateDto.getAcceptanceNotificationDeadline());
        }

        if(updateDto.getUploadingPaperDeadline() != null) {
            conference.setUploadingPaperDeadline(updateDto.getUploadingPaperDeadline());
        }

        conferenceRepository.save(conference);

        return ResponseEntity.ok("Success");
    }
}
