package com.active_you.userservice.repository;

import com.active_you.userservice.models.PersonFollow;
import com.active_you.userservice.models.PersonFollowId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonFollowRepository extends JpaRepository<PersonFollow, Long> {

    PersonFollow findById(PersonFollowId id);
}
