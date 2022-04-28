package io.winnie.usingspringjdbctemplate.repository;

import io.winnie.usingspringjdbctemplate.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
