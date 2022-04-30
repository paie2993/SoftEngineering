package com.yakuza.backend.Controller;

import com.yakuza.backend.Controller.DTO.AddPaperDto;
import com.yakuza.backend.Controller.DTO.PaperInfoDto;
import com.yakuza.backend.Model.Conference;
import com.yakuza.backend.Model.Keyword;
import com.yakuza.backend.Model.Paper;
import com.yakuza.backend.Model.TopicOfInterest;
import com.yakuza.backend.Model.UserModel.Author;
import com.yakuza.backend.Repository.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/paper")
public class PaperController {
    private final PaperRepository paperRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final ConferenceRepository conferenceRepository;
    private final AuthorRepository authorRepository;
    private final KeywordRepository keywordRepository;


    public PaperController(PaperRepository paperRepository, UserRepository userRepository, TopicRepository topicRepository, ConferenceRepository conferenceRepository, AuthorRepository authorRepository, KeywordRepository keywordRepository) {
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.conferenceRepository = conferenceRepository;
        this.authorRepository = authorRepository;
        this.keywordRepository = keywordRepository;
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

    @ApiOperation(value = "Submit a paper")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource not found")
    })
    @PostMapping("/")
    public ResponseEntity<?> addPaper(@ApiIgnore Principal principal, @RequestBody @Valid AddPaperDto request) {
        var conferenceOpt = conferenceRepository.findById(request.getConferenceId());
        var author = authorRepository.getByUsername(principal.getName());

        if(conferenceOpt.isEmpty()) {
            return new ResponseEntity<>("Conference not found", HttpStatus.NOT_FOUND);
        }

        Conference conference = conferenceOpt.get();

        Paper paper = new Paper();
        paper.setPaperAbstract(request.getPaperAbstract());
        paper.setConference(conference);
        paper.setStatus("pending");
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
}
