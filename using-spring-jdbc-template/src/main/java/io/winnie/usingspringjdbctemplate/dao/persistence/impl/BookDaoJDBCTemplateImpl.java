package io.winnie.usingspringjdbctemplate.dao.persistence.impl;

import io.winnie.usingspringjdbctemplate.dao.BookDao;
import io.winnie.usingspringjdbctemplate.dao.persistence.mapper.BookRowMapper;
import io.winnie.usingspringjdbctemplate.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BookDaoJDBCTemplateImpl implements BookDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    public BookDaoJDBCTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", getBookRowMapper(),id);
    }
    
    @Override
    public Book findByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE title = ?", getBookRowMapper(), title);
    }
    
    @Override
    public Book save(Book book) {
        jdbcTemplate.update("INSERT INTO book(isbn, publisher, title, author_id) VALUES (?, ?, ?, ?)"
                , book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId());
        
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(id);
    }
    
    @Override
    public Book update(Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, isbn = ?, publisher = ?, author_id = ? WHERE id = ?",
                               book.getTitle(), book.getIsbn(), book.getPublisher(), book.getAuthorId(), book.getId());
        
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(id);
    }
    
    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }
    
    private RowMapper<Book> getBookRowMapper() {
        return new BookRowMapper();
    }
}
