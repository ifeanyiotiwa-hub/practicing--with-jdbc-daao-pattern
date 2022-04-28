package io.codewithme.daopattern.jdbc.repository;

import io.codewithme.daopattern.jdbc.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
