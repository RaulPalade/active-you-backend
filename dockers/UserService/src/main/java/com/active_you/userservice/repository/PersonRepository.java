package com.active_you.userservice.repository;

import com.active_you.userservice.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByName(String name);

    @NonNull
    Optional<Person> findById(@NonNull Long id);
    @Modifying
    @Query("INSERT INTO Person_Follow (follower_id, followee_id) VALUES (:followerId, :followeeId)")
    void addFollower(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

    @Modifying
    @Query("DELETE from Person_Follow WHERE (follower_id=id1 AND followee_id=id2)")
    void removeFollower(@Param("followerId") Long id1, @Param("followeeId") Long id2);

}
