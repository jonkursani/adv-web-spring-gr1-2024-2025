package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.book.BookDto;
import dev.jonkursani.restapigr1.dtos.book.CreateBookRequest;
import dev.jonkursani.restapigr1.dtos.book.UpdateBookRequest;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();
    BookDto findById(long id);
    BookDto create(CreateBookRequest request);
    void update(long id, UpdateBookRequest request);
    void delete(long id);
}

