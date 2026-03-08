package com.edy.first_restapi.Mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edy.first_restapi.Mapper.Mapper;
import com.edy.first_restapi.domain.dto.BookDto;
import com.edy.first_restapi.domain.entities.BookEntity;


@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    @Override
    public BookDto mapto(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapfrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }

}
