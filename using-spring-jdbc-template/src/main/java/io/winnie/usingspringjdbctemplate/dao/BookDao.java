package io.winnie.usingspringjdbctemplate.dao;

import io.winnie.usingspringjdbctemplate.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAllBooks();
    
    Book getById(Long id);
    
    Book findByTitle(String title);
    
    Book save(Book book);
    
    Book update(Book book);
    
    void deleteById(Long id);
}
