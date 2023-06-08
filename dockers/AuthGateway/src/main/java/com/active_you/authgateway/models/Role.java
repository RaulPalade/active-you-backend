package com.active_you.authgateway.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons;

    @Override
    public String toString() {
        return "{\"id\": " + id +
                ", \"name\": \"" + name + "\"" +
                ", \"persons\": " + (persons != null ? persons.toString() : "null") +
                "}";
    }
}
