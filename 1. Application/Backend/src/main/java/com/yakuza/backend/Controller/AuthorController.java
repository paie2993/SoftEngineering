package com.yakuza.backend.Controller;


import com.yakuza.backend.Controller.DTO.AuthorDto;
import com.yakuza.backend.Controller.DTO.PaperInfoDto;
import com.yakuza.backend.Model.Paper;
import com.yakuza.backend.Model.UserModel.Author;
import com.yakuza.backend.Repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/author")
public class AuthorController {
    private final PaperRepository paperRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final ConferenceRepository conferenceRepository;
    private final AuthorRepository authorRepository;
    private final KeywordRepository keywordRepository;

    public AuthorController(PaperRepository paperRepository, UserRepository userRepository, TopicRepository topicRepository, ConferenceRepository conferenceRepository, AuthorRepository authorRepository, KeywordRepository keywordRepository) {
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.conferenceRepository = conferenceRepository;
        this.authorRepository = authorRepository;
        this.keywordRepository = keywordRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAuthors() {
        var authors = authorRepository.findAll();
        Set<AuthorDto> authorSet = new HashSet<>();

        for(var author: authors) {
            authorSet.add(new AuthorDto(author));
        }

        return ResponseEntity.ok(authorSet);
    }

    @GetMapping("/papers")
    public ResponseEntity<?> getPapersForAuthor(@ApiIgnore Principal principal) {
        Set<PaperInfoDto> paperInfoDtoSet = new HashSet<>();
        Author author = authorRepository.getByUsername(principal.getName());
        var papers = author.getPapers();

        for(var paper: papers) {
            paperInfoDtoSet.add(new PaperInfoDto(paper));
        }

        return ResponseEntity.ok(paperInfoDtoSet);
    }
}
