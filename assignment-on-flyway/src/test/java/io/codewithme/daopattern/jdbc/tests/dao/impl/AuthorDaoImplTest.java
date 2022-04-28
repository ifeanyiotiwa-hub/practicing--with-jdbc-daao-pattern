package io.codewithme.daopattern.jdbc.tests.dao.impl;

import io.codewithme.daopattern.jdbc.dao.service.AuthorDao;
import io.codewithme.daopattern.jdbc.entity.Author;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"io.codewithme.daopattern.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorDaoImplTest.class);
    @Autowired
    AuthorDao authorDao;
    
    @Test
    public void testGetAuthorById() {
        Author author = authorDao.getById(1L);
        
        assertThat(author).isNotNull();
    }
    
    @Test
    public void testFindAuthorByFirstNameAndLastName() {
        Author author = authorDao.findByFirstNameAndLastName("Craig", "Walls");
        assertThat(author).isNotNull();
    }
    
    @Test
    public void testSaveAuthor() {
        Author author = new Author("Istar", "Winnie");
        Author savedAuthor = authorDao.save(author);
        
        assertThat(savedAuthor).isNotNull();
    }
    
    @Test
    public void testUpdateAuthor(){
        Author author = new Author("Chigozie", "Ugo");
        Author saved = authorDao.save(author);
        
        saved.setLastName("Otiwa");
        Author updateAuthor = authorDao.updateAuthor(saved);
        assertThat(updateAuthor.getLastName()).isEqualTo("Otiwa");
    }
    
    @Test
    public void testUpdateAuthor2(){
        Author author = new Author("Chigozie", "Ugo");
        Author saved = authorDao.save(author);
        
        saved.setFirstName("Otiwa");
        Author updateAuthor = authorDao.updateAuthor(saved);
        assertThat(updateAuthor.getFirstName()).isEqualTo("Otiwa");
    }
    
    @Test
    public void testDeleteAuthorById() {
        Author author = new Author("Tommy", "Jerry");
        Author saved = authorDao.save(author);
        LOG.info("id: {}", saved.getId());
        authorDao.deleteById(saved.getId());
        Author authorAfterDelete = authorDao.getById(saved.getId());
        assertThat(authorAfterDelete).isNull();
        
    }
    
}