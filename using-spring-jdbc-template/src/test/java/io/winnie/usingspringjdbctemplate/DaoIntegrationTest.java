package io.winnie.usingspringjdbctemplate;

import io.winnie.usingspringjdbctemplate.dao.AuthorDao;
import io.winnie.usingspringjdbctemplate.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by jt on 8/20/21.
 */

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.winnie.usingspringjdbctemplate.dao"})
public class DaoIntegrationTest {
    
    @Autowired
    AuthorDao authorDao;
    
    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        
        Author saved = authorDao.saveNewAuthor(author);
        
        authorDao.deleteAuthorById(saved.getId());
        
        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(saved.getId()));
        
    }
    
    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        
        Author saved = authorDao.saveNewAuthor(author);
        
        saved.setLastName("Thompson");
        Author updated = authorDao.updateAuthor(saved);
        
        assertThat(updated.getLastName()).isEqualTo("Thompson");
    }
    
    @Test
    void testInsertAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        
        Author saved = authorDao.saveNewAuthor(author);
        
        assertThat(saved).isNotNull();
    }
    
    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        
        assertThat(author).isNotNull();
    }
    
    @Test
    void testGetAuthor() {
        
        Author author = authorDao.getById(1L);
        System.err.println(author);
        assertThat(author.getId()).isNotNull();
    }
}
