package com.edy.first_restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edy.first_restapi.domain.entities.AuthorEntity;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

}
