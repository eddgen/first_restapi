package com.edy.first_restapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.edy.first_restapi.Mapper.Mapper;
import com.edy.first_restapi.domain.dto.AuthorDto;
import com.edy.first_restapi.domain.entities.AuthorEntity;
import com.edy.first_restapi.services.AuthorService;

import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapfrom(authorDto);
        AuthorEntity savedAuthorEntity =  authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapto(savedAuthorEntity), HttpStatus.CREATED);
    }
    
    @GetMapping("/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authorEntities = authorService.findAll();
        return authorEntities.stream()
            .map(authorEntity -> authorMapper.mapto(authorEntity))
            .toList();
    }
    
}
