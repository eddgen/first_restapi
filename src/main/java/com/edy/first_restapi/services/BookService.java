package com.edy.first_restapi.services;

import com.edy.first_restapi.domain.entities.BookEntity;


public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
} 
