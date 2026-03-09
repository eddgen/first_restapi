package com.edy.first_restapi.services;


import java.util.List;

import com.edy.first_restapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();
}
