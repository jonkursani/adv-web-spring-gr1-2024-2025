package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.dtos.book.BookDto;
import dev.jonkursani.restapigr1.dtos.book.CreateBookRequest;
import dev.jonkursani.restapigr1.dtos.book.UpdateBookRequest;
import dev.jonkursani.restapigr1.repositories.BookRepository;
import dev.jonkursani.restapigr1.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public List<BookDto> findAll() {
        return repository.findAll()
                .stream()
                .map(book -> new BookDto(book.getId(), book.getTitle(), book.getAuthor()))
                .toList();
    }

    @Override
    public BookDto findById(long id) {
        return repository.findById(id)
                .map(book -> new BookDto(book.getId(), book.getTitle(), book.getAuthor()))
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    }

    @Override
    public BookDto create(CreateBookRequest request) {
//        var bookToSave = new Book(0, request.getTitle(), request.getAuthor());
//        var savedBook = repository.save(bookToSave);
//        return new BookDto(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor());
        return new BookDto();
    }

    @Override
    public void update(long id, UpdateBookRequest request) {
//        var bookFromDb = findById(id);
//        if (bookFromDb != null) {
//            var bookToUpdate = new Book(id, request.getTitle(), request.getAuthor());
//            repository.save(bookToUpdate);
//        }
    }

    @Override
    public void delete(long id) {
        var bookFromDb = findById(id);
        if (bookFromDb != null) {
            repository.deleteById(id);
        }
    }
}
