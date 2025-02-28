package dev.jonkursani.restapigr1.repositories;

import dev.jonkursani.restapigr1.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
