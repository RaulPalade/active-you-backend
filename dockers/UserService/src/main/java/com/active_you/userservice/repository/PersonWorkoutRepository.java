package com.active_you.userservice.repository;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.models.PersonWorkout;
import com.active_you.userservice.models.Workout;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface PersonWorkoutRepository extends JpaRepository<PersonWorkout, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE PersonWorkout pw SET pw.completed = true, pw.endDate = :endDate WHERE pw.person = :person AND pw.workout = :workout")
    void removeWorkout(Person person, Workout workout, Timestamp endDate);
}
