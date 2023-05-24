package com.active_you.workoutservice.service;

import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> findAll(){return workoutRepository.findAll();}

    public Optional<Workout> getById(Long id){return workoutRepository.findById(id);}

    public List<Workout> getByName(String name){return workoutRepository.findAllByName(name);}

    public Workout addWorkout(Workout newWorkout) {return workoutRepository.save(newWorkout);}

    public boolean deleteById(Long id) {workoutRepository.deleteById(id);
        return true;
    }
}
