package io.winnie.usingspringjdbctemplate.dao;

import io.winnie.usingspringjdbctemplate.entity.Book;

public interface BookDao {
    Book getById(Long id);
    
    Book findByTitle(String title);
    
    Book save(Book book);
    
    Book update(Book book);
    
    void deleteById(Long id);
}
