package io.winnie.usingspringjdbctemplate.repository;

import io.winnie.usingspringjdbctemplate.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
