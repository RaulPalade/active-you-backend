package com.active_you.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Data
@ToString
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"from_person_fk", "to_person_fk"}))
public class PersonFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "from_person_fk")
    private Person from;

    @ManyToOne
    @JoinColumn(name = "to_person_fk")
    private Person to;

    public PersonFollow() {

    }

    public Long getFromId() {
        return to.getId();
    }

    public Long getToId() {
        return from.getId();
    }
}