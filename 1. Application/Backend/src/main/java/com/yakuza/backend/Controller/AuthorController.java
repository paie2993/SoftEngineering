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
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
