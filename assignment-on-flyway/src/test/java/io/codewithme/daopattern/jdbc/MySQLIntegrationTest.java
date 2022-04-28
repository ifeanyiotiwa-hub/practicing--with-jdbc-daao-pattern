package io.codewithme.daopattern.jdbc;

import io.codewithme.daopattern.jdbc.entity.Author;
import io.codewithme.daopattern.jdbc.entity.Book;
import io.codewithme.daopattern.jdbc.repository.AuthorRepository;
import io.codewithme.daopattern.jdbc.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(MySQLIntegrationTest.class);
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Test
    public void testBookSaves() {
        Author author = new Author();
        author.setId(1L);
        Book newBook = new Book("JPA Remastered", "1234",author, "Oreilly");
        LOG.warn("newBook {}", newBook);
        Book savedBook = bookRepository.save(newBook);
        LOG.info("savedBook {}",savedBook);
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook).isNotNull();
    }
    
    @Test
    public void testAuthorSaves() {
        long count = authorRepository.count();
        
        Author savedAuthor = authorRepository.save(new Author());
        long count2 = authorRepository.count();
        assertThat(count2).isGreaterThan(count);
        assertThat(savedAuthor.getId()).isNotNull();
        assertThat(savedAuthor).isNotNull();
    }
}
