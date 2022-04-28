package io.codewithme.daojdbc.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;


@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    private String firstName;
    private String lastName;
    
    public Author() {
    }
    
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Author.class.getSimpleName() + " = \n{", "\n}")
                .add("\t\"id\":" + id + "\"")
                .add("\t\"firstName\":" + firstName + "\"")
                .add("\t\"lastName\":" + lastName + "\"")
                .toString();
    }
}
