package io.codewithme.daojdbc.persistence.daoimpl;

import io.codewithme.daojdbc.entity.Author;
import io.codewithme.daojdbc.persistence.daoservice.AuthorDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.codewithme.dao.jdbc.persistence"})
class AuthorDaoIntegrationTest {
    
    @Autowired
    private AuthorDao authorDao;
    
    @Test
    public void testGetById() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }
}