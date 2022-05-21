package com.yakuza.backend.Controller;

import com.yakuza.backend.Controller.DTO.*;
import com.yakuza.backend.Model.BidForPaper;
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
@CrossOrigin(origins = "*")
@Validated
@RequestMapping("/conference")
public class ConferenceController {
    private final ConferenceRepository conferenceRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final PaperRepository paperRepository;
    private final ConferenceSessionRepository conferenceSessionRepository;
    private final PaperConferenceSubmissionRepository paperConferenceSubmissionRepository;
    private final BidForPaperRepository bidForPaperRepository;
    private final ReviewerRepository reviewerRepository;

    public ConferenceController(ConferenceRepository conferenceRepository, UserRepository userRepository, TopicRepository topicRepository, PaperRepository paperRepository, ConferenceSessionRepository conferenceSessionRepository, PaperConferenceSubmissionRepository paperConferenceSubmissionRepository, BidForPaperRepository bidForPaperRepository, ReviewerRepository reviewerRepository) {
        this.conferenceRepository = conferenceRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.paperRepository = paperRepository;
        this.conferenceSessionRepository = conferenceSessionRepository;
        this.paperConferenceSubmissionRepository = paperConferenceSubmissionRepository;
        this.bidForPaperRepository = bidForPaperRepository;
        this.reviewerRepository = reviewerRepository;
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

        var submissions = conference.getSubmissions();
        Set<PaperSubmissionsDto> paperSubmissionsDtos = new HashSet<>();

        for(var sub: submissions) {
            paperSubmissionsDtos.add(new PaperSubmissionsDto(sub));
        }

        return ResponseEntity.ok(paperSubmissionsDtos);
    }

    @PostMapping("{id}/sessions/{session_id}/papers")
    @ApiOperation("Assign a paper to a session in a conference")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Conference/paper not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> assignPaperToSession(@ApiIgnore Principal principal, @PathVariable Integer id, @PathVariable Integer session_id, @RequestBody AddPaperToSessionDto dto) {
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

        var sessionOpt = conferenceSessionRepository.findConferenceSessionByIdAndConferenceId(session_id, id);

        if(sessionOpt.isEmpty()) {
            return new ResponseEntity<>("Session not found", HttpStatus.NOT_FOUND);
        }

        var subOpt = paperConferenceSubmissionRepository.findPaperConferenceSubmissionByConferenceIdAndPaperId(id, dto.getPaperId());

        // if the submission for the paper doesn't exist
        if(subOpt.isEmpty()) {
            return new ResponseEntity<>("Paper was not submitted to this conference", HttpStatus.BAD_REQUEST);
        }

        var submission = subOpt.get();
        System.out.println(submission.getStatus());
        // if the submission isn't accepted
        if(!Objects.equals(submission.getStatus(), "accepted")) {
            return new ResponseEntity<>("Paper was not accepted", HttpStatus.BAD_REQUEST);
        }

        var session = sessionOpt.get();
        var paper = submission.getPaper();

        if(session.getPapers().contains(paper)) {
            return new ResponseEntity<>("Paper already submitted to conference", HttpStatus.BAD_REQUEST);
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

        var subOpt = paperConferenceSubmissionRepository.findPaperConferenceSubmissionByConferenceIdAndPaperId(id, paperId);

        if(subOpt.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }

        var sub = subOpt.get();


        // if the paper was already added to a conference session, the decision cannot be modified
        if(Objects.equals(sub.getStatus(), "accepted")) {
            if(sub.getConference() != null) {
                return new ResponseEntity<>("Paper already assigned to conference session, you may not change the " +
                        "decision", HttpStatus.BAD_REQUEST);
            }
        }

        if(Objects.equals(status.getContent(), "accepted")) {
            sub.setStatus("accepted");
        } else if(Objects.equals(status.getContent(), "rejected")) {
            sub.setStatus("rejected");
        } else return new ResponseEntity<>("Invalid status", HttpStatus.BAD_REQUEST);

        paperConferenceSubmissionRepository.save(sub);

        return ResponseEntity.ok("Success");
    }

    @PutMapping("/{id}/papers/assign")
    @ApiOperation("Assigns accepted papers to reviewers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Conference not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> assignPapers(@ApiIgnore Principal principal, @PathVariable Integer id) {
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

        var paper_submissions = conference.getSubmissions();

        // go over all the submissions, and assign the accepted papers to the highest bidding reviewer

        for(var sub: paper_submissions) {
            if(Objects.equals(sub.getStatus(), "accepted")) {
                var paper = sub.getPaper();

                // get the highest bidder for the paper
                Optional<BidForPaper> bid_opt = bidForPaperRepository.findTopByPaperIdOrderByInterestDesc(paper.getId());

                if(bid_opt.isPresent()) {
                    var bid = bid_opt.get();
                    var reviewer = bid.getReviewer();
                    var paperSet = reviewer.getAssignedPapers();

                    paperSet.add(bid.getPaper());
                    reviewer.setAssignedPapers(paperSet);
                    reviewerRepository.save(reviewer);
                }
            }
        }

        return ResponseEntity.ok("Success");
    }
}
