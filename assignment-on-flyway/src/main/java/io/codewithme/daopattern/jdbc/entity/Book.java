package io.codewithme.daopattern.jdbc.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT")
    private Long id;
    private String title;
     private String isbn;
     @Transient
     private Author authorId;
     private String publisher;
    
    public Long getId() {
        return id;
    }
    
    public Book() {
    }
    
    public Book(String title, String isbn, Author authorId, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.authorId = authorId;
        this.publisher = publisher;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public Author getAuthorId() {
        return authorId;
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
    
    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && Objects.equals(authorId,
                book.authorId) && Objects.equals(publisher, book.publisher);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, isbn, authorId, publisher);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(" ", "\n" + Book.class.getSimpleName() + " : {\n", "}")
                .add("\"id\": \"" + id + "'" )
                .add("\"title\": \"" + title + "\",\n")
                .add("\"isbn\": \"" + isbn + "\",\n")
                .add("\"publisher\": \"" + publisher + "\",\n")
                .add("\"author\": \"" + authorId + "\"\n")
                .toString();
    }
}
