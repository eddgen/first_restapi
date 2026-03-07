package com.edy.first_restapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.edy.first_restapi.Mapper.Mapper;
import com.edy.first_restapi.domain.dto.AuthorDto;
import com.edy.first_restapi.domain.entities.AuthorEntity;
import com.edy.first_restapi.services.AuthorService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AuthorController {

    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping("/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto author) {
        AuthorEntity authorEntity = authorMapper.mapfrom(author);
        AuthorEntity savedAuthorEntity =  authorService.createAuthor(authorEntity);
        return authorMapper.mapto(savedAuthorEntity);
    }
    
    @GetMapping("/author")
    public String getAuthor(@RequestParam String param) {
        return new String();
    }
    
}
