package io.winnie.usingspringjdbctemplate.dao.persistence.impl;

import io.winnie.usingspringjdbctemplate.dao.AuthorDao;
import io.winnie.usingspringjdbctemplate.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author getById(Long id) {
        return null;
    }
    
    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }
    
    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }
    
    @Override
    public Author updateAuthor(Author author) {
        return null;
    }
    
    @Override
    public void deleteAuthorById(Long id) {
    
    }
}
