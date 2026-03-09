package com.edy.first_restapi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.edy.first_restapi.Mapper.Mapper;
import com.edy.first_restapi.domain.dto.BookDto;
import com.edy.first_restapi.domain.entities.BookEntity;
import com.edy.first_restapi.services.BookService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BookController {

    private BookService bookService;

    private Mapper<BookEntity, BookDto> bookMapper;
    
    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }
    
    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(
        @PathVariable("isbn") String isbn,
        @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapfrom(bookDto);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapto(savedBookEntity), HttpStatus.CREATED);
    }
    
}
