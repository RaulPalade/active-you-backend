package Utils;

import com.active_you.workoutservice.models.Exercise;
import com.active_you.workoutservice.models.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutQueueMessage {
    private Workout workout;
    private Exercise exercise;
    private Long workoutIdForExercise;
    private String action;

    @Override
    public String toString() {
        return "{" +
                "\"workout\": " + (workout != null ? workout.toString() : "null") + "," +
                "\"exercise\": " + (exercise != null ? exercise.toString() : "null") + "," +
                "\"workoutId\": " + (workoutIdForExercise != null ? workoutIdForExercise : "null") + "," +
                "\"action\": \"" + action + "\"" +
                "}";
    }
}