package io.winnie.usingspringjdbctemplate;


import io.winnie.usingspringjdbctemplate.dao.BookDao;
import io.winnie.usingspringjdbctemplate.dao.persistence.impl.BookDaoJDBCTemplateImpl;
import io.winnie.usingspringjdbctemplate.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.winnie.usingspringjdbctemplate.dao"})
public class BookDaoIntegrationTest {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private BookDao bookdao;
    
    @BeforeEach
    void setUp() {
        bookdao = new BookDaoJDBCTemplateImpl(jdbcTemplate);
    }
    
    @Test
    void testGetById() {
        Book book = bookdao.getById(1L);
        
        assertThat(book).isNotNull();
    }
    @Test
    void testFindByTitle() {
        Book book = bookdao.findByTitle("Clean Code");
        assertThat(book).isNotNull();
    }
    @Test
    void testSave() {
        Book book = new Book();
        book.setTitle("Spring in Action 2");
        book.setPublisher("oreilly");
        Book saved = bookdao.save(book);
        assertThat(saved).isNotNull();
    }
    @Test
    void testUpdate() {
        Book book = new Book("Spring RM", "ISBN00000", "O'Reilly");
        book.setAuthorId(1L);
        Book saved = bookdao.save(book);
        
        saved.setTitle("Spring RM 2nd Edition");
        Book updated = bookdao.update(saved);
        
        assertThat(updated.getTitle()).isEqualTo("Spring RM 2nd Edition");
    }
    @Test
    void testDeleteById() {
        Book newBook = new Book("Delete Test", "DeleteISBN", "O'Reilly");
        Book saved = bookdao.save(newBook);
        
        bookdao.deleteById(saved.getId());
        assertThrows(EmptyResultDataAccessException.class, () -> bookdao.getById(saved.getId()));
        
    }
}
