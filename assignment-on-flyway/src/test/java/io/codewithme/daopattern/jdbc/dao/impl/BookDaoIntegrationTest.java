package io.codewithme.daopattern.jdbc.dao.impl;

import io.codewithme.daopattern.jdbc.dao.service.AuthorDao;
import io.codewithme.daopattern.jdbc.dao.service.BookDao;
import io.codewithme.daopattern.jdbc.entity.Author;
import io.codewithme.daopattern.jdbc.entity.Book;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"io.codewithme.daopattern.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoIntegrationTest {
    private static final Logger LOG = LoggerFactory.getLogger(BookDaoIntegrationTest.class);
    
    
    @Autowired
    BookDao bookDao;
    
    @Autowired
    AuthorDao authorDao;
    
    @Test
    void testGetById() {
    
        Book book = new Book("Spring In Action", "ISBN024680", 2L, "Oreilly");
        Book savedBook = bookDao.save(book);
        long id = savedBook.getId();
        assertThat(bookDao.getById(id)).isNotNull();
        assertThat(bookDao.getById(id)).isEqualTo(savedBook);
    }
    
    @Test
    void testFindByTitle() {
        Book book = bookDao.findByTitle("Spring JPA Remastered 2");
        assertThat(book).isNotNull();
    }
    
    @Test
    void testSave() {
        Book newBook = new Book("Introduction to Java", "ISBN024680", 1L, "Oreilly");
        Book saved = bookDao.save(newBook);
        assertThat(saved).isNotNull();
    }
    
    @Test
    void testUpdate() {
        Book book = new Book("How To program in Java 7th Edition", "ISBN024680", 2L, "Oreilly");
        Book saved = bookDao.save(book);
        saved.setTitle("How To Program In Java 8th Edition");
        Book updated = bookDao.update(saved);
        assertThat(updated.getTitle()).isEqualTo("How To Program In Java 8th Edition");
    }
    
    @Test
    void testUpdateById() {
        Book book = bookDao.getById(7L);
        book.setTitle("How To program in Java 9th Edition");
        Book updated = bookDao.updateById(7L, book);
        assertThat(updated.getTitle()).isEqualTo("How To program in Java 9th Edition");
    }
    
    @Test
    void testDelete() {
        Book book = new Book("Test Title", "ISBN000", 2L, "Oreilly");
        Book saved = bookDao.save(book);
        bookDao.delete(saved);
        Long id = saved.getId();
        assertThat(bookDao.getById(id)).isNull();
    }
    
    @Test
    void testDeleteById() {
        Book book = new Book("Test Title", "ISBN000", 2L, "Oreilly");
        Book saved = bookDao.save(book);
        
        bookDao.deleteById(saved.getId());
        assertThat(bookDao.getById(saved.getId())).isNull();
    }
    
    @Test
    void testFindAllByPublisher() {
        List<Book> listOfBook = bookDao.findAllByPublisher("Oreilly");
        int count = 0;
        listOfBook.forEach(book -> {
            if ("publisher".equals(book.getPublisher())) {
                LOG.info(book.toString());
            }
        });
        LOG.info("Total count is {}", listOfBook.size());
        assertThat(listOfBook.size()).isGreaterThan(2);
    }
}