package com.edy.first_restapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public BookEntity save(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return booksRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(booksRepository.findAll().spliterator(),false).toList();
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable){
        return booksRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findByIsbn(String isbn) {
        return booksRepository.findById(isbn);
    }

    @Override
    public boolean exists(String isbn) {
        return booksRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn,BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
         Optional<BookEntity> existingBookEntity = booksRepository.findById(isbn);
         return existingBookEntity.map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return booksRepository.save(existingBook);
         }).orElseThrow(() -> new RuntimeException("Book not found"));
         
    }

    @Override
    public void delete(String isbn) {
        booksRepository.deleteById(isbn);
    }

    
}
