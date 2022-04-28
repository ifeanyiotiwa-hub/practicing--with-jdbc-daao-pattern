package io.winnie.usingspringjdbctemplate.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.StringJoiner;

@Entity
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    
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
    
    @Override
    public String toString() {
        return new StringJoiner("", Author.class.getSimpleName() + "= \n    {\n", "\n    }")
                .add("\"id\": \"" + id + "\",\n")
                .add("\"firstName\": \"" + firstName + "\",\n")
                .add("\"lastName\": \"" + lastName + "\"")
                .toString();
    }
}