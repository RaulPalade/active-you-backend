package com.active_you.workoutservice.repository;

import com.active_you.workoutservice.models.PersonWorkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PersonWorkoutRepository extends JpaRepository<PersonWorkout, Long> {

    List<PersonWorkout> getPersonWorkoutByIdPerson(Long personId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE PersonWorkout pw SET pw.completed = true, pw.endDate = :endDate WHERE pw.id = :id")
    void deleteWorkoutForUser(Long id, Timestamp endDate);
}
