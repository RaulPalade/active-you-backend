package com.active_you.userservice.repository;

import com.active_you.userservice.models.PersonFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PersonFollowRepository extends JpaRepository<PersonFollow, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from person_follow where from_person_fk = :fromId and to_person_fk = :toId", nativeQuery = true)
    void unfollow(Long fromId, Long toId);
}
