package com.edy.first_restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.edy.first_restapi.domain.entities.BookEntity;

@Repository
public interface BooksRepository extends CrudRepository<BookEntity, String>,
        PagingAndSortingRepository<BookEntity, String> {

}
