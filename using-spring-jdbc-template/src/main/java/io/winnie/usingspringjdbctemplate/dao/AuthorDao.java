package io.winnie.usingspringjdbctemplate.dao;

import io.winnie.usingspringjdbctemplate.entity.Author;

public interface AuthorDao {
    Author getById(Long id);
    
    Author findAuthorByName(String firstName, String lastName);
    
    Author saveNewAuthor(Author author);
    
    Author updateAuthor(Author author);
    
    void deleteAuthorById(Long id);
}
