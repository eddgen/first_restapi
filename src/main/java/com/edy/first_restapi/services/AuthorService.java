package com.edy.first_restapi.services;


import java.util.List;
import java.util.Optional;

import com.edy.first_restapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity save(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findById(Long id);

    boolean exists(long id);

    AuthorEntity partialUpdate(long id, AuthorEntity newAuthorEntity);

    void deleteAuthor(long id);
}
