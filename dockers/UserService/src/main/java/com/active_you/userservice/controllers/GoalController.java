package com.active_you.userservice.controllers;

import com.active_you.userservice.models.Goal;
import com.active_you.userservice.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/goals")
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping("/addGoal")
    public Goal addGoal(@RequestBody Goal goal) {
        return goalService.addGoal(goal);
    }

    @DeleteMapping("/delete/{id}")
    public boolean removeGoal(@PathVariable Long id) {
        return goalService.delete(id);
    }
}
