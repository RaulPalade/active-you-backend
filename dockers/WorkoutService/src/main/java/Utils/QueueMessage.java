package Utils;

import com.active_you.workoutservice.models.Exercise;
import com.active_you.workoutservice.models.Workout;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueueMessage {
    private Long workoutId;
    private Long createdBy;
    private Workout workout;
    private Exercise exercise;
    private String action;

    public QueueMessage(Long createdBy, Workout workout, String action) {
        this.createdBy = createdBy;
        this.workout = workout;
        this.action = action;
    }

    public QueueMessage(Long workoutId, Exercise exercise, String action) {
        this.workoutId = workoutId;
        this.exercise = exercise;
        this.action = action;
    }
}