package io.codewithme.daopattern.jdbc.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
//    @Type(type = "org.hibernate.type.UUIDCharType")
    private Long id;
    
    private String firstName;
    private String lastName;
    
    public Long getId() {
        return id;
    }
    
    
    public Author() {
    }
    
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(" ", "\n" + Author.class.getSimpleName() + " = {\n", "}")
                .add("\t\"id\": \"" + id + "\",\n")
                .add("\t\"firstName\": \"" + firstName + "\",\n")
                .add("\t\"lastName\": \"" + lastName + "\"\n")
                .toString();
    }
}
