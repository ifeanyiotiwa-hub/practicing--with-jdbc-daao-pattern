package io.codewithme.daopattern.jdbc.dao.impl;

import io.codewithme.daopattern.jdbc.dao.service.AuthorDao;
import io.codewithme.daopattern.jdbc.entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {
    
    private static final Logger LOG = LoggerFactory.getLogger(AuthorDaoImpl.class);
    public static final String FIND_AUTHOR_BY_FIRSTNAME_AND_LASTNAME = "SELECT * FROM author WHERE first_name = ? AND" +
                                                                               " last_name " +
                                                                               "= ?";
    public static final String SAVE_AUTHOR = "INSERT INTO author(first_name, last_name) VALUES (?, ?)";
    private final DataSource dataSource;
    
    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public Author getById(Long id) {
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = dataSource.getConnection();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE id = ?");
            // resultSet=statement.executeQuery("SELECT * FROM author WHERE id = " + id);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        return null;
    }
    
    @Override
    public Author findByFirstNameAndLastName(String firstName, String lastName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_FIRSTNAME_AND_LASTNAME);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
        return null;
    }
    
    @Override
    public Author save(Author author) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_AUTHOR);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.execute();
        
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()"); // get last insert id
            if (resultSet.next()) {
                long savedId = resultSet.getLong(1);
                return getById(savedId);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
        return null;
    }
    
    @Override
    public Author updateAuthor(Author author) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE author SET first_name = ?, last_name = ? WHERE id" +
                                                                    " = ?");
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setLong(3, author.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
        return getById(author.getId());
    }
    
    @Override
    public void deleteById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
    
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM author WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(null, preparedStatement, connection);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
    }
    
    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        LOG.info("Returned Author: {}", author);
        return author;
    }
    
    private void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        
        if (connection != null) {
            connection.close();
        }
    }
}
