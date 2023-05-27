package com.active_you.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Entity
@Table
public class PersonFollow {

    @EmbeddedId
    private PersonFollowId id;

    @ManyToOne
    @MapsId("fromId")
    @JoinColumn(name = "from_person_fk")
    private Person from;

    @ManyToOne
    @MapsId("toId")
    @JoinColumn(name = "to_person_fk")
    private Person to;


    public PersonFollow() {

    }

    public PersonFollow(Person from, Person to) {
        this.from = from;
        this.to = to;
    }

    public Long getFromId() {
        return to.getId();
    }

    public Long getToId() {
        return from.getId();
    }
}
