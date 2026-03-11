package com.edy.first_restapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
        public AuthorEntity save(AuthorEntity authorEntity) {
            return authorRepository.save(authorEntity);
        }


        @Override
        public List<AuthorEntity> findAll() {
            return StreamSupport.stream(authorRepository.
                                findAll()
                                .spliterator(), 
                                false)
                    .toList();
        }


        @Override
        public Optional<AuthorEntity> findById(Long id) {
            return authorRepository.findById(id);
        }


        @Override
        public boolean exists(long id) {
            return authorRepository.existsById(id);
        }


        @Override
        public AuthorEntity partialUpdate(long id, AuthorEntity authorEntity) {

            Optional<AuthorEntity> existingAuthorEntity = authorRepository.findById(id);
            return existingAuthorEntity.map(existingAuthor -> {
                Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
                Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
                return authorRepository.save(existingAuthor);
            }).orElseThrow(() -> new RuntimeException("Author does not exist"));
        }


        @Override
        public void deleteAuthor(long id) {
            authorRepository.deleteById(id);
        }

        


       


        
}
