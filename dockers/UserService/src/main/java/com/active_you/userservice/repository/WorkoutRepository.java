package com.active_you.userservice.repository;


import com.active_you.userservice.models.Workout;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into person_workout values(id_person = :personId, id_workout = :idWorkout)", nativeQuery = true)
    void markWorkoutCompleted(Long personId, Long idWorkout);
}
