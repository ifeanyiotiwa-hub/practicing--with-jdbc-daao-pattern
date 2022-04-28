package io.winnie.usingspringjdbctemplate.dao.persistence.extractor;

import io.winnie.usingspringjdbctemplate.dao.persistence.mapper.AuthorMapper;
import io.winnie.usingspringjdbctemplate.entity.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorResultSetExtractor implements ResultSetExtractor<Author> {
    @Override
    public Author extractData(ResultSet rs) throws SQLException, DataAccessException {
        return new AuthorMapper().mapRow(rs,0);
    }
}
