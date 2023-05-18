package com.active_you.workoutservice.repository;

import com.active_you.workoutservice.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
