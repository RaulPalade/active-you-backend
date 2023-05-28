package com.active_you.userservice.repository;

import com.active_you.userservice.models.Goal;
import com.active_you.userservice.models.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Set;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Transactional
    Set<Goal> findGoalByPerson(Person person);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Goal g SET g.completed = true, g.endDate = :endDate WHERE g.person = :person AND g.id = :goalId")
    void removeGoal(Person person, Long goalId, Timestamp endDate);
}
