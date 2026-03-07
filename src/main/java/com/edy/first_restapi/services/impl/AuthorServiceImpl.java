package com.edy.first_restapi.services.impl;

import org.springframework.stereotype.Service;

import com.edy.first_restapi.domain.entities.AuthorEntity;
import com.edy.first_restapi.repositories.AuthorRepository;
import com.edy.first_restapi.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {


        private AuthorRepository authorRepository;

        
        public AuthorServiceImpl(AuthorRepository authorRepository) {
            this.authorRepository = authorRepository;
        }


        @Override
        public AuthorEntity createAuthor(AuthorEntity authorEntity) {
            return authorRepository.save(authorEntity);
        }

}
