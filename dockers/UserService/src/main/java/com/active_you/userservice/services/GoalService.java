package com.active_you.userservice.services;

import com.active_you.userservice.models.Goal;
import com.active_you.userservice.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoalService {
    final GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal addGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public boolean delete(Long id) {
        goalRepository.deleteById(id);
        return true;
    }
}
