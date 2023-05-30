package com.active_you.workoutservice.repository;

import com.active_you.workoutservice.models.Exercise;
import com.active_you.workoutservice.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Transactional
    List<Exercise> findExerciseByWorkout(Workout workout);
}
