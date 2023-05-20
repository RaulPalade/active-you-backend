package com.active_you.userservice.controllers;

import com.active_you.userservice.models.Goal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/goals")
public class GoalController {
    @PostMapping("/addGoal")
    public Goal addGoal(@RequestBody Goal goal) {
        // TODO
        return null;
    }

    @PostMapping("/addGoal/{id}")
    public Goal removeGoal(@PathVariable Long id) {
        // TODO
        return null;
    }
}
