package io.codewithme.daopattern.jdbc.dao.service;

import io.codewithme.daopattern.jdbc.entity.Book;

import java.util.List;

public interface BookDao {
    Book getById(Long id);
    Book findByTitle(String title);
    Book save(Book book);
    Book update(Book book);
    
    Book updateById(Long id, Book book);
    void delete(Book book);
    
    void deleteById(Long id);
    List<Book> findAllByPublisher(String publisher);
}
