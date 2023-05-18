package com.active_you.workoutservice.repository;

import com.active_you.workoutservice.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
