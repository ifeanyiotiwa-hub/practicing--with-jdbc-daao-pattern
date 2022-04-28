package io.winnie.usingspringjdbctemplate.entity;
import javax.persistence.*;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    
    @Transient
    private List<Book> books;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    @Override
    public String toString() {
        return new StringJoiner("", "\n" + Author.class.getSimpleName() + "= \n    {\n", "\n    }")
                .add("\t\t\"id\": \"" + id + "\",\n")
                .add("\t\t\"firstName\": \"" + firstName + "\",\n")
                .add("\t\t\"lastName\": \"" + lastName + "\",\n")
                .add("\t\t\"books\": \"" + books + "\"")
                .toString();
    }
}