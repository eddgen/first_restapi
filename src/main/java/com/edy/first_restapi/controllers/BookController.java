package com.edy.first_restapi.controllers;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;



@RestController
public class BookController {

    private BookService bookService;

    private Mapper<BookEntity, BookDto> bookMapper;
    
    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }
    
    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(
        @PathVariable("isbn") String isbn,
        @RequestBody BookDto bookDto) {

        if (!bookService.exists(isbn)) {
            BookEntity bookEntity = bookMapper.mapfrom(bookDto);
            BookEntity savedBookEntity = bookService.save(isbn, bookEntity);
            return new ResponseEntity<>(bookMapper.mapto(savedBookEntity), 
                                        HttpStatus.CREATED);
        }

        BookEntity updatedBookEntity = bookMapper.mapfrom(bookDto);
        BookEntity savedBookEntity = bookService.save(isbn, updatedBookEntity);
        BookDto responseBookDto = bookMapper.mapto(savedBookEntity);
        return new ResponseEntity<>(responseBookDto, HttpStatus.OK);
        
    }

    @GetMapping("/books")
    public Page<BookDto> listBooks(Pageable pageable){
        Page<BookEntity> bookEntities = bookService.findAll(pageable);
        return bookEntities.map(bookMapper::mapto);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> findByIsbn(@PathVariable String isbn) {
        Optional<BookEntity> OptionalBookEntity = bookService.findByIsbn(isbn);
        return OptionalBookEntity.map(bookEntity ->{
            BookDto bookDto = bookMapper.mapto(bookEntity);
            return new ResponseEntity<>(bookDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(
        @PathVariable String isbn,
        @RequestBody BookDto bookDto){

        if(!bookService.exists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookEntity bookEntity = bookMapper.mapfrom(bookDto);
        BookEntity savedBookEntity = bookService.partialUpdate(isbn,bookEntity);
        BookDto responseBook = bookMapper.mapto(savedBookEntity);
        return new ResponseEntity<>(responseBook,HttpStatus.OK);
        
        }
    
    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn){
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}
