package com.edy.first_restapi.services;


import com.edy.first_restapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);
}
