package com.yakuza.backend.Controller;

import com.yakuza.backend.Controller.DTO.*;
import com.yakuza.backend.Model.TopicOfInterest;
import com.yakuza.backend.Repository.ReviewerRepository;
import com.yakuza.backend.Repository.TopicRepository;
import io.swagger.annotations.Api;
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
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@Validated
@RequestMapping("/reviewer")
public class ReviewerController {
    private final ReviewerRepository reviewerRepository;
    private final TopicRepository topicRepository;

    public ReviewerController(ReviewerRepository reviewerRepository, TopicRepository topicRepository) {
        this.reviewerRepository = reviewerRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping("/topics")
    @ApiOperation("Get the topics of interest")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Reviewer not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> getTopicsOfInterest(@ApiIgnore Principal principal) {
        var reviewerOptional = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOptional.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var reviewer = reviewerOptional.get();

        Set<TopicOfInterestDto> topicOfInterestSet = reviewer.getTopicsOfInterest()
                .stream()
                .map(topic -> new TopicOfInterestDto(topic))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(topicOfInterestSet);
    }

    @GetMapping("/nottopics")
    @ApiOperation("Get the topics of interest that a reviewer doesn't have")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Reviewer not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> getTopicsOfDisinterest(@ApiIgnore Principal principal) {
        var reviewerOptional = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOptional.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var reviewer = reviewerOptional.get();

        var allTopics = topicRepository.findAll();
        var dtoSet = new HashSet<TopicOfInterestDto>();

        for(var topic: allTopics) {
            if(!topic.getReviewers().contains(reviewer)) {
                dtoSet.add(new TopicOfInterestDto(topic));
            }
        }

        return ResponseEntity.ok(dtoSet);
    }

    @PutMapping("/topics")
    @ApiOperation("Modify the topics of interest")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Reviewer not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    public ResponseEntity<?> modifyTopicsOfInterest(@ApiIgnore Principal principal, @RequestBody ReviewerTopicOfInterestDto dto) {
        var reviewerOptional = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOptional.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var reviewer = reviewerOptional.get();

        if(dto.getTopics() != null) {
            Set<TopicOfInterest> topicOfInterestSet = new HashSet<>();

            for(String topicDesc: dto.getTopics()) {
                var topicOpt = topicRepository.findByDescription(topicDesc);

                if(topicOpt.isPresent()) {
                    TopicOfInterest topicOfInterest = topicOpt.get();
                    topicOfInterestSet.add(topicOfInterest);
                } else {
                    TopicOfInterest topicOfInterest = new TopicOfInterest();
                    topicOfInterest.setDescription(topicDesc);
                    topicRepository.save(topicOfInterest);
                    topicOfInterestSet.add(topicOfInterest);
                }
            }

            reviewer.setTopicsOfInterest(topicOfInterestSet);
        }

        reviewerRepository.save(reviewer);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/papers")
    @ApiOperation("Get the papers assigned to review")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Reviewer not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    ResponseEntity<?> getAssignedPapers(@ApiIgnore Principal principal) {
        var reviewerOptional = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOptional.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var reviewer = reviewerOptional.get();

        var papers = reviewer.getAssignedPapers();

        Set<PaperInfoDto> paperInfoDtoSet = new HashSet<>();

        for(var paper: papers) {
            paperInfoDtoSet.add(new PaperInfoDto(paper));
        }

        return ResponseEntity.ok(paperInfoDtoSet);
    }

    @GetMapping("/papers/toreview")
    @ApiOperation("Get the papers assigned to review but that have not yet been reviewed")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Reviewer not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    ResponseEntity<?> getToReview(@ApiIgnore Principal principal) {
        var reviewerOptional = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOptional.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var reviewer = reviewerOptional.get();

        var papers = reviewer.getAssignedPapers();

        Set<PaperInfoDto> paperInfoDtoSet = new HashSet<>();

        for(var paper: papers) {
            if(paper.getReviewerEvaluations().isEmpty()) {
                paperInfoDtoSet.add(new PaperInfoDto(paper));
            }
        }

        return ResponseEntity.ok(paperInfoDtoSet);
    }

    @GetMapping("/bids")
    @ApiOperation("Get all the bids of a reviewer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Reviewer not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    ResponseEntity<?> getBids(@ApiIgnore Principal principal) {
        var reviewerOptional = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOptional.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var reviewer = reviewerOptional.get();

        var bids = reviewer.getBidsForPaper();

        Set<ReviewerBidDto> bidSet = new HashSet<>();

        for(var bid: bids) {
            var bidDto = new ReviewerBidDto();
            bidDto.setBidValue(bid.getInterest());
            bidDto.setReviewerId(reviewer.getId());
            bidDto.setPaperId(bid.getPaper().getId());
            bidSet.add(bidDto);
        }

        return ResponseEntity.ok(bidSet);
    }

    @GetMapping("/papers/evaluations")
    @ApiOperation("Get the reviewed papers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Reviewer not found"),
            @ApiResponse(code = 503, message = "Unauthorized")
    })
    ResponseEntity<?> getReviewedPapers(@ApiIgnore Principal principal) {
        var reviewerOptional = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOptional.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var reviewer = reviewerOptional.get();

        var papers = reviewer.getReviewerEvaluations();

        Set<PaperEvaluationDto> paperEvaluationDtos = new HashSet<>();

        for(var eval: papers) {
            paperEvaluationDtos.add(new PaperEvaluationDto(eval.getJudgement(), new PaperInfoDto(eval.getPaper())));
        }

        return ResponseEntity.ok(paperEvaluationDtos);
    }
}
