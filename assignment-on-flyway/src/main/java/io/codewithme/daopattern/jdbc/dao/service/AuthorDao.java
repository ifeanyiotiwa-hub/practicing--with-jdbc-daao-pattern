package io.codewithme.daopattern.jdbc.dao.service;

import io.codewithme.daopattern.jdbc.entity.Author;

public interface AuthorDao {
    
    Author getById(Long id);
    
    Author findByFirstNameAndLastName(String firstName, String lastName);
    
    Author save(Author author);
    
    Author updateAuthor(Author saved);
    
    void deleteById(Long id);
}
