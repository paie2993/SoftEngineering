package com.yakuza.backend.Controller;

import com.yakuza.backend.Controller.DTO.*;
import com.yakuza.backend.Model.*;
import com.yakuza.backend.Model.UserModel.Author;
import com.yakuza.backend.Model.UserModel.Reviewer;
import com.yakuza.backend.Repository.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@Validated
@RequestMapping("/paper")
public class PaperController {
    private final PaperRepository paperRepository;
    private final TopicRepository topicRepository;
    private final ConferenceRepository conferenceRepository;
    private final AuthorRepository authorRepository;
    private final KeywordRepository keywordRepository;
    private final PaperConferenceSubmissionRepository paperConferenceSubmissionRepository;
    private final ReviewerRepository reviewerRepository;
    private final BidForPaperRepository bidForPaperRepository;
    private final ConflictOfInterestRepository conflictOfInterestRepository;
    private final PaperEvaluationRepository paperEvaluationRepository;
    private final PaperCommentRepository paperCommentRepository;


    public PaperController(PaperRepository paperRepository, UserRepository userRepository, TopicRepository topicRepository, ConferenceRepository conferenceRepository, AuthorRepository authorRepository, KeywordRepository keywordRepository, PaperConferenceSubmissionRepository paperConferenceSubmissionRepository, ReviewerRepository reviewerRepository, BidForPaperRepository bidForPaperRepository, ConflictOfInterestRepository conflictOfInterestRepository, PaperEvaluationRepository paperEvaluationRepository, PaperCommentRepository paperCommentRepository) {
        this.paperRepository = paperRepository;
        this.topicRepository = topicRepository;
        this.conferenceRepository = conferenceRepository;
        this.authorRepository = authorRepository;
        this.keywordRepository = keywordRepository;
        this.paperConferenceSubmissionRepository = paperConferenceSubmissionRepository;
        this.reviewerRepository = reviewerRepository;
        this.bidForPaperRepository = bidForPaperRepository;
        this.conflictOfInterestRepository = conflictOfInterestRepository;
        this.paperEvaluationRepository = paperEvaluationRepository;
        this.paperCommentRepository = paperCommentRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getPapers() {
        var papers = paperRepository.findAll();
        Set<PaperInfoDto> paperInfoDtoSet = new HashSet<>();

        for(var paper: papers) {
            paperInfoDtoSet.add(new PaperInfoDto(paper));
        }

        return ResponseEntity.ok(paperInfoDtoSet);
    }

    @ApiOperation(value = "Get a specific paper")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaper(@PathVariable Integer id) {
        var paperOpt = paperRepository.findById(id);

        if(paperOpt.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new PaperInfoDto(paperOpt.get()));
    }


    @GetMapping("/unassigned")
    public ResponseEntity<?> getUnassignedPapers() {
        var papers = paperRepository.findAll();
        Set<PaperInfoDto> paperInfoDtoSet = new HashSet<>();

        for(var paper: papers) {
            if(paper.getReviewers().isEmpty()) {
                paperInfoDtoSet.add(new PaperInfoDto(paper));
            }
        }

        return ResponseEntity.ok(paperInfoDtoSet);
    }

    @ApiOperation(value = "Submit a paper")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @PostMapping("/")
    public ResponseEntity<?> addPaper(@ApiIgnore Principal principal, @RequestBody @Valid AddPaperDto request) {
        var author = authorRepository.getByUsername(principal.getName());

        Paper paper = new Paper();
        paper.setPaperAbstract(request.getPaperAbstract());
        paper.setTitle(request.getTitle());

        Set<TopicOfInterest> topicOfInterestSet = new HashSet<>();

        for(String topicDesc: request.getTopics()) {
            var topicOptional = topicRepository.findByDescription(topicDesc);

            if(topicOptional.isPresent()) {
                // if the topic already exists
                TopicOfInterest topicOfInterest = topicOptional.get();
                topicOfInterestSet.add(topicOfInterest);
            } else {
                TopicOfInterest topicOfInterest = new TopicOfInterest();
                topicOfInterest.setDescription(topicDesc);
                topicRepository.save(topicOfInterest);
                topicOfInterestSet.add(topicOfInterest);
            }
        }

        paper.setTopicsOfInterest(topicOfInterestSet);

        Set<Keyword> keywordSet = new HashSet<>();

        for(String keywordName: request.getKeywords()) {
            var keywordOptional = keywordRepository.findByName(keywordName);

            if(keywordOptional.isPresent()) {
                // if the keyword already exists
                Keyword keyword = keywordOptional.get();
                keywordSet.add(keyword);
            } else {
                Keyword keyword = new Keyword();
                keyword.setName(keywordName);
                keywordRepository.save(keyword);
                keywordSet.add(keyword);
            }
        }

        paper.setKeywords(keywordSet);

        Set<Author> authorSet = new HashSet<>();

        for(String authorName: request.getOtherAuthors()) {
            var authorOpt = authorRepository.findByUsername(authorName);

            if(authorOpt.isEmpty()) {
                return new ResponseEntity<>("Author " + authorName + " doesn't exist", HttpStatus.NOT_FOUND);
            } else {
                Author author1 = authorOpt.get();
                authorSet.add(author1);
            }
        }

        authorSet.add(author);

        paper.setAuthors(authorSet);

        paperRepository.save(paper);

        return ResponseEntity.ok("Success");
    }

    @ApiOperation(value = "Submit a paper to a conference")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @PutMapping("/{id}/submissions")
    public ResponseEntity<?> submitPaperToConference(@ApiIgnore Principal principal, @PathVariable Integer id, @RequestBody @Valid SubmitToConferenceDto request) {
        var paperOpt = paperRepository.findById(id);
        var confOpt = conferenceRepository.findById(request.getConferenceId());
        var authorOpt = authorRepository.findByUsername(principal.getName());

        if(paperOpt.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }
        if(confOpt.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }
        if(authorOpt.isEmpty()) {
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }

        var paper = paperOpt.get();
        var conference = confOpt.get();
        var author = authorOpt.get();

        // if the author isn't an author of the paper, return forbidden

        if(!paper.getAuthors().contains(author)) {
            return new ResponseEntity<>("You're not an author of the paper", HttpStatus.FORBIDDEN);
        }

        // if the paper was already submitted to the conference
        var submissionOpt = paperConferenceSubmissionRepository.findPaperConferenceSubmissionByConferenceIdAndPaperId(conference.getId(), paper.getId());

        if(submissionOpt.isPresent()) {
            return new ResponseEntity<>("Paper already submitted to this conference", HttpStatus.BAD_REQUEST);
        }


        // save the new submission to the repo
        PaperConferenceSubmission paperConferenceSubmission = new PaperConferenceSubmission();
        paperConferenceSubmission.setConference(conference);
        paperConferenceSubmission.setPaper(paper);
        paperConferenceSubmission.setStatus("pending");

        paperConferenceSubmissionRepository.save(paperConferenceSubmission);

        return ResponseEntity.ok("Success");
    }

    @ApiOperation(value = "Add a bid to a paper (reviewer only)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @PostMapping("/{id}/bids")
    public ResponseEntity<?> bidForPaper(@ApiIgnore Principal principal, @PathVariable Integer id, @RequestBody BidDto bidDto) {
        var reviewerOpt = reviewerRepository.findByUsername(principal.getName());
        var paperOpt = paperRepository.findById(id);

        if(paperOpt.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }
        if(reviewerOpt.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        var bid_val = bidDto.getBidValue();
        // a bid may only have an interest from 1 to 10
        if(bid_val > 10 || bid_val <= 0) {
            return new ResponseEntity<>("Invalid bid value, must be between 1 and 10", HttpStatus.BAD_REQUEST);
        }

        BidForPaper new_bid = new BidForPaper();

        new_bid.setInterest(bid_val);
        new_bid.setReviewer(reviewerOpt.get());
        new_bid.setPaper(paperOpt.get());

        bidForPaperRepository.save(new_bid);

        return ResponseEntity.ok("Success");
    }

    @ApiOperation(value = "Add a conflict of interest to a paper")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @Transactional
    @PostMapping("/{id}/conflicts")
    public ResponseEntity<?> addConflict(@ApiIgnore Principal principal, @PathVariable Integer id, @RequestBody AddConflictDTO dto) {
        var reviewerOpt = reviewerRepository.findByUsername(principal.getName());
        var paperOpt = paperRepository.findById(id);

        if(paperOpt.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }
        if(reviewerOpt.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        if(dto.getDescription() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var reviewer = reviewerOpt.get();
        var paper = paperOpt.get();

        bidForPaperRepository.deleteAllByReviewerIdAndPaperId(reviewer.getId(), id);

        ConflictOfInterest conflictOfInterest = new ConflictOfInterest();
        conflictOfInterest.setReviewer(reviewer);
        conflictOfInterest.setPaper(paper);
        conflictOfInterest.setDescription(dto.getDescription());

        var ass_papers = reviewer.getAssignedPapers();
        ass_papers.remove(paper);
        reviewer.setAssignedPapers(ass_papers);
        reviewerRepository.save(reviewer);

        conflictOfInterestRepository.save(conflictOfInterest);

        Optional<BidForPaper> bid_opt = bidForPaperRepository.findTopByPaperIdOrderByInterestDesc(paper.getId());

        if(bid_opt.isPresent()) {
            var bid = bid_opt.get();
            var new_reviewer = bid.getReviewer();
            var paperSet = new_reviewer.getAssignedPapers();

            paperSet.add(bid.getPaper());
            new_reviewer.setAssignedPapers(paperSet);
            reviewerRepository.save(new_reviewer);
        }

        return ResponseEntity.ok("Success");
    }

    @ApiOperation(value = "Evaluate a paper")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @Transactional
    @PostMapping("/{id}/evaluations")
    public ResponseEntity<?> addEvaluation(@ApiIgnore Principal principal, @PathVariable Integer id, @RequestBody AddEvaluationDTO dto) {
        var reviewerOpt = reviewerRepository.findByUsername(principal.getName());
        var paperOpt = paperRepository.findById(id);

        if(paperOpt.isEmpty()) {
            return new ResponseEntity<>("Paper not found", HttpStatus.NOT_FOUND);
        }
        if(reviewerOpt.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        if(dto.getDecision() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var reviewer = reviewerOpt.get();
        var paper = paperOpt.get();

        var evaluation = new ReviewerEvaluation();
        evaluation.setJudgement(dto.getDecision());
        evaluation.setPaper(paper);
        evaluation.setReviewer(reviewer);
        paperEvaluationRepository.save(evaluation);

        return ResponseEntity.ok("Success");
    }

    @ApiOperation(value = "Add a special comment to a paper")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @Transactional
    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addSpecialComment(@ApiIgnore Principal principal, @PathVariable Integer id, @RequestBody AddCommentDto dto) {
        var reviewerOpt = reviewerRepository.findByUsername(principal.getName());

        if(reviewerOpt.isEmpty()) {
            return new ResponseEntity<>("Reviewer not found", HttpStatus.NOT_FOUND);
        }

        Reviewer reviewer = reviewerOpt.get();

        var evalOpt = paperEvaluationRepository.findByReviewerIdAndPaperId(reviewer.getId(), id);

        if(evalOpt.isEmpty()) {
            return new ResponseEntity<>("Paper not found in your reviewed papers", HttpStatus.NOT_FOUND);
        }

        var eval = evalOpt.get();
        var paper = eval.getPaper();

        var comment = new PaperComment();
        comment.setContent(dto.getComment());
        comment.setReviewer(reviewer);
        comment.setPaper(paper);
        paperCommentRepository.save(comment);

        return ResponseEntity.ok("Success");
    }
}
