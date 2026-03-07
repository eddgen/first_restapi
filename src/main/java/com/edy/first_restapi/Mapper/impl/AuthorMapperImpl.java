package com.edy.first_restapi.Mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edy.first_restapi.Mapper.Mapper;
import com.edy.first_restapi.domain.dto.AuthorDto;
import com.edy.first_restapi.domain.entities.AuthorEntity;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {

        private ModelMapper modelMapper;

        public AuthorMapperImpl(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }
        @Override
        public AuthorDto mapto(AuthorEntity authorEntity) {
            return modelMapper.map(authorEntity, AuthorDto.class);
        }

    
        @Override
        public AuthorEntity mapfrom(AuthorDto authorDto) {
            return modelMapper.map(authorDto, AuthorEntity.class);
        }
}