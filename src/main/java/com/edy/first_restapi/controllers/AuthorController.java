package com.edy.first_restapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.edy.first_restapi.Mapper.Mapper;
import com.edy.first_restapi.domain.dto.AuthorDto;
import com.edy.first_restapi.domain.entities.AuthorEntity;
import com.edy.first_restapi.services.AuthorService;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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
        AuthorEntity savedAuthorEntity =  authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapto(savedAuthorEntity), HttpStatus.CREATED);
    }
    
    @GetMapping("/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authorEntities = authorService.findAll();
        return authorEntities.stream()
            .map(authorEntity -> authorMapper.mapto(authorEntity))
            .toList();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDto> listAuthorById(@PathVariable Long id){
        Optional<AuthorEntity> OptionalAuthorEntity = authorService.findById(id);
        return OptionalAuthorEntity.map(authorEntity -> {
            AuthorDto authorDto = authorMapper.mapto(authorEntity);
            return new ResponseEntity<>(authorDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(
        @PathVariable long id, 
        @RequestBody AuthorDto authorDto) {

        if(!authorService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);
        AuthorEntity newAuthorEntity = authorMapper.mapfrom(authorDto);
        AuthorEntity savedAuthor = authorService.save(newAuthorEntity);
        AuthorDto responseAuthor = authorMapper.mapto(savedAuthor);
        return new ResponseEntity<>(responseAuthor,HttpStatus.OK); 
    }

    @PatchMapping("authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(
        @PathVariable long id,
        @RequestBody AuthorDto authorDto) {

        if(!authorService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity newAuthorEntity = authorMapper.mapfrom(authorDto);
        AuthorEntity savedAuthor = authorService.partialUpdate(id, newAuthorEntity);
        AuthorDto responseAuthor = authorMapper.mapto(savedAuthor);
        return new ResponseEntity<>(responseAuthor,HttpStatus.OK); 
    }

    @DeleteMapping("authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
     



