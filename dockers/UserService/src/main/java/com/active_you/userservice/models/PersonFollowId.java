package com.active_you.userservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PersonFollowId implements Serializable {

    @Column(name = "from_person_fk")
    private Long fromId;

    @Column(name = "to_person_fk")
    private Long toId;

    public PersonFollowId() {

    }

    public PersonFollowId(Long fromId, Long toId) {
        this.fromId = fromId;
        this.toId = toId;
    }
}