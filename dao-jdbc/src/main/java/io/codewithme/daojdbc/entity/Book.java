package io.codewithme.daojdbc.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    private String title;
    private String isbn;
    private String publisher;
    private Long authorId;
    
    public Long getId() {
        return id;
    }
    
    public Book() {
    }
    
    
    public String getTitle() {
        return title;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public Long getAuthorId() {
        return authorId;
    }
    
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && Objects.equals(publisher, book.publisher);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, publisher);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + " = \n{", "\n}")
                .add("\t\"id\":" + "\"" + id + "\"")
                .add("\t\"title\":" + "\"" + title + "\"")
                .add("\t\"isbn\":" + "\"" + isbn + "\"")
                .add("\t\"publisher\":" + "\""  + publisher + "\"")
                .toString();
    }
}
