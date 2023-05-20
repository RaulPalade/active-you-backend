package com.active_you.userservice.repository;

import com.active_you.userservice.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
