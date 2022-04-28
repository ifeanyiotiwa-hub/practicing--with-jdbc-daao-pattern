package io.codewithme.daopattern.jdbc.dao.impl;

import io.codewithme.daopattern.jdbc.dao.service.AuthorDao;
import io.codewithme.daopattern.jdbc.dao.service.BookDao;
import io.codewithme.daopattern.jdbc.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {
    
    private static final Logger LOG = LoggerFactory.getLogger(BookDaoImpl.class);
    
    public static final String SAVE_BOOK = "INSERT INTO book(title, isbn, author_id, publisher) values (?, ?, ?, ?)";
    private final DataSource dataSource;
    private final AuthorDao authorDao;
    
    public BookDaoImpl(DataSource dataSource, AuthorDao authorDao) {
        this.dataSource = dataSource;
        this.authorDao = authorDao;
    }
    
    @Override
    public Book getById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return getBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(rs, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    @Override
    public Book save(Book book) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(SAVE_BOOK);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            
            if (book.getAuthorId() != null) {
                ps.setLong(3, book.getAuthorId().getId());
            }
            ps.setString(4, book.getPublisher());
            ps.execute();
            
            //retrieve previously saved Id
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
            
            if (rs.next()) {
                long id = rs.getLong(1);
                return getById(id);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(rs, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    @Override
    public Book findByTitle(String title) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book WHERE title = ?");
            ps.setString(1, title);
            rs = ps.executeQuery();
        
            if (rs.next()) {
                return getBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(rs, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    @Override
    public Book update(Book book) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("UPDATE book set title = ?, isbn = ?, author_id = ?, publisher = ? WHERE" +
                                                     " id = ?");
            ps.setString(1,book.getTitle());
            ps.setString(2,book.getIsbn());
            
            if (book.getAuthorId() != null) {
                ps.setLong(3,book.getAuthorId().getId());
            } {
                ps.setNull(3, -5);
            }
            
            ps.setString(4,book.getPublisher());
            ps.setLong(5, book.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(rs, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getById(book.getId());
    }
    
    
    public Book updateById(Long id, Book book){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("UPDATE book set title = ?, isbn = ?, author_id = ?, publisher = ? WHERE" +
                                                     " id = ?");
            ps.setString(1,book.getTitle());
            ps.setString(2,book.getIsbn());
            
            if (book.getAuthorId() != null) {
                ps.setLong(3,book.getAuthorId().getId());
            } else {
                ps.setNull(3, -5);
            }
            ps.setString(4,book.getPublisher());
            ps.setLong(5, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(rs, ps, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getById(book.getId());
    }
    
    @Override
    public void delete(Book book) {
        deleteById(book.getId());
    }
    
    @Override
    public void deleteById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
    
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("DELETE FROM book WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(null, ps, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
    
    }
    
    @Override
    public List<Book> findAllByPublisher(String publisher) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> listOfBooks = new ArrayList<>();
    
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT  * FROM book WHERE publisher = ?");
            ps.setString(1, publisher);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = getBookFromResultSet(rs);
                listOfBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(rs, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listOfBooks;
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
    
    private Book getBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthorId(authorDao.getById(rs.getLong("author_id")));
        book.setPublisher(rs.getString("publisher"));
        LOG.info("Saved Book: \n{}", book);
        return book;
    }
}
