package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.dtos.book.BookDto;
import dev.jonkursani.restapigr1.dtos.book.CreateBookRequest;
import dev.jonkursani.restapigr1.dtos.book.UpdateBookRequest;
import dev.jonkursani.restapigr1.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(service.findAll()); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id)); // 200
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public ResponseEntity<BookDto> create(@RequestBody CreateBookRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody UpdateBookRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build(); // 204
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
