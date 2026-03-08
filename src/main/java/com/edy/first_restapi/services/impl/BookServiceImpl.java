package com.edy.first_restapi.services.impl;

import org.springframework.stereotype.Service;

import com.edy.first_restapi.domain.entities.BookEntity;
import com.edy.first_restapi.repositories.BooksRepository;
import com.edy.first_restapi.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return booksRepository.save(book);
    }
}
