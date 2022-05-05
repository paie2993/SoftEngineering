package com.yakuza.backend.Controller;

import com.yakuza.backend.Controller.DTO.*;
import com.yakuza.backend.Model.Conference;
import com.yakuza.backend.Model.TopicOfInterest;
import com.yakuza.backend.Model.UserModel.CMSUser;
import com.yakuza.backend.Repository.*;
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
import java.util.Objects;
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
    private final PaperRepository paperRepository;
    private final ConferenceSessionRepository conferenceSessionRepository;

    public ConferenceController(ConferenceRepository conferenceRepository, UserRepository userRepository, TopicRepository topicRepository, PaperRepository paperRepository, ConferenceSessionRepository conferenceSessionRepository) {
        this.conferenceRepository = conferenceRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.paperRepository = paperRepository;
        this.conferenceSessionRepository = conferenceSessionRepository;
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Conference not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> updateConferenceDetails(@ApiIgnore Principal principal, @PathVariable Integer id, @RequestBody ConferenceUpdateRequestDto updateDto) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(id);
        CMSUser user = userRepository.findByUsername(principal.getName());

        // if the conference wasn't found, return 404
        if(conferenceOptional.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }

        // if the user isn't the chair of the conference, return unauthorized
        Conference conference = conferenceOptional.get();

        if(!Objects.equals(conference.getChair().getId(), user.getId())) {
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

    @GetMapping("/{id}/papers")
    @ApiOperation(value = "Get all the papers from a conference")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Conference not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> getPapers(@ApiIgnore Principal principal, @PathVariable Integer id) {
        var conferenceOptional = conferenceRepository.findById(id);
        CMSUser user = userRepository.getCMSUserByUsername(principal.getName());

        if(conferenceOptional.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }

        var conference = conferenceOptional.get();

        if(!Objects.equals(conference.getChair().getId(), user.getId())) {
            return new ResponseEntity<>("You are not the chair of this conference", HttpStatus.UNAUTHORIZED);
        }

        Set<PaperInfoDto> result = new HashSet<>();

        for(var paper: conference.getPapers()) {
            result.add(new PaperInfoDto(paper));
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("{id}/sessions/{session_id}/papers")
    @ApiOperation("Assign a paper to a session in a conference")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Conference/paper not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> assignPaperToSession(@ApiIgnore Principal principal, @PathVariable Integer id, @PathVariable Integer session_id, @RequestBody AddPaperToSessionDto dto) {
        var conferenceOptional = conferenceRepository.findById(id);
        CMSUser user = userRepository.getCMSUserByUsername(principal.getName());

        if(conferenceOptional.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }

        var conference = conferenceOptional.get();

        if(!Objects.equals(conference.getChair().getId(), user.getId())) {
            return new ResponseEntity<>("You are not the chair of this conference", HttpStatus.UNAUTHORIZED);
        }

        var paperOptional = paperRepository.findById(dto.getPaperId());

        if(paperOptional.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }

        var paper = paperOptional.get();

        if(!Objects.equals(paper.getConference().getId(), conference.getId())) {
            return new ResponseEntity<>("Paper doesn't belong to conference", HttpStatus.FORBIDDEN);
        }

        var sessionOptional = conferenceSessionRepository.findById(session_id);

        if(sessionOptional.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }

        var session = sessionOptional.get();

        if(!Objects.equals(session.getConference().getId(), conference.getId())) {
            return new ResponseEntity<>("Session not in provided conference", HttpStatus.FORBIDDEN);
        }

        if(!paper.getConferenceSessions().isEmpty()) {
            return new ResponseEntity<>("Paper already assigned to a session", HttpStatus.BAD_REQUEST);
        }

        if(!paper.getStatus().equals("accepted")) {
            return new ResponseEntity<>("Paper has not been accepted", HttpStatus.BAD_REQUEST);
        }

        session.getPapers().add(paper);
        conferenceSessionRepository.save(session);

        return ResponseEntity.ok("Success");
    }

    @PutMapping("/{id}/papers/{paperId}/decideOnPaper")
    @ApiOperation("Decide upon a paper")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Conference/paper not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> decideOnPaper(@ApiIgnore Principal principal, @PathVariable Integer id, @PathVariable Integer paperId, @RequestBody StatusDecisionDto status) {
        var conferenceOptional = conferenceRepository.findById(id);
        CMSUser user = userRepository.getCMSUserByUsername(principal.getName());

        if(conferenceOptional.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }

        var conference = conferenceOptional.get();

        if(!Objects.equals(conference.getChair().getId(), user.getId())) {
            return new ResponseEntity<>("You are not the chair of this conference", HttpStatus.UNAUTHORIZED);
        }

        var paperOptional = paperRepository.findById(paperId);

        if(paperOptional.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }

        var paper = paperOptional.get();

        if(!Objects.equals(paper.getConference().getId(), conference.getId())) {
            return new ResponseEntity<>("Paper doesn't belong to conference", HttpStatus.FORBIDDEN);
        }

        if (!Objects.equals(paper.getStatus(), "pending")) {
            return new ResponseEntity<>("Paper's status has already been decided upon", HttpStatus.FORBIDDEN);
        }

        if(Objects.equals(status.getContent(), "rejected")) {
            paper.setStatus("rejected");
        } else if (status.getContent().equals("accepted")) {
            paper.setStatus("accepted");
        } else return new ResponseEntity<>("Invalid status: can only be accepted/rejected", HttpStatus.BAD_REQUEST);

        paperRepository.save(paper);

        return ResponseEntity.ok("Success");
    }

}
