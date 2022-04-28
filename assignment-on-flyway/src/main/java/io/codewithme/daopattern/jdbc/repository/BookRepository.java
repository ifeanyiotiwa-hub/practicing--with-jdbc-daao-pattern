package io.codewithme.daopattern.jdbc.repository;

import io.codewithme.daopattern.jdbc.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
