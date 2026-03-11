package com.edy.first_restapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edy.first_restapi.domain.entities.BookEntity;


public interface BookService {
    BookEntity save(String isbn, BookEntity book);

    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    boolean exists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
} 
