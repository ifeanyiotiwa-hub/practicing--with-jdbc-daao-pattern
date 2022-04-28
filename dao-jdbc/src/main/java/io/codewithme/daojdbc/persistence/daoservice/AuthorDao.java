package io.codewithme.daojdbc.persistence.daoservice;

import io.codewithme.daojdbc.entity.Author;

public interface AuthorDao {
    
    Author getById(Long id);
}
