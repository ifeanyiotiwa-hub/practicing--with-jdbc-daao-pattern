package io.winnie.usingspringjdbctemplate.dao.persistence.impl;

import io.winnie.usingspringjdbctemplate.dao.AuthorDao;
import io.winnie.usingspringjdbctemplate.dao.persistence.extractor.AuthorResultSetExtractor;
import io.winnie.usingspringjdbctemplate.dao.persistence.mapper.AuthorMapper;
import io.winnie.usingspringjdbctemplate.entity.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Author getById(Long id) {
        String sql = "SELECT author.id as id, first_name, last_name, book.id as id, title, isbn, publisher from " +
                             "author left outer join book on author.id = book.author_id where author_id = ?";
        //return jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(),id);
        
        return jdbcTemplate.query(sql,getAuthorResultSetExtractor(), id);
    }
    
    private ResultSetExtractor<Author> getAuthorResultSetExtractor() {
        return new AuthorResultSetExtractor();
    }
    
    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name = ? AND last_name = ?", getRowMapper()
                ,firstName,
                lastName);
    }
    
    @Override
    public Author saveNewAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO author(first_name, last_name) VALUES (?, ?)"
                                , author.getFirstName()
                                , author.getLastName());
        
        Long lastSavedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(lastSavedId);
    }
    
    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update("UPDATE author SET first_name = ?, last_name = ? WHERE id =?",author.getFirstName(),
                author.getLastName(), author.getId());
        Long lastSavedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(lastSavedId);
    }
    
    @Override
    public void deleteAuthorById(Long id) {
        jdbcTemplate.update("DELETE FROM author WHERE id = ?", id);
    }
    
    private RowMapper<Author> getRowMapper() {
        return new AuthorMapper();
    }
}
