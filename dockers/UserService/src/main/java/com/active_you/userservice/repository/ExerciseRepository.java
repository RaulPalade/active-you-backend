package com.active_you.userservice.repository;

import com.active_you.userservice.models.Exercise;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT e FROM Exercise e WHERE e.id = :workoutId", nativeQuery = true)
    Set<Exercise> findByWorkoutId(Long workoutId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into exercise_workout values (:exerciseId, :workoutId)", nativeQuery = true)
    void addExerciseToWorkout(Long exerciseId, Long workoutId);
}
