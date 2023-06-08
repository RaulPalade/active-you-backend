package com.active_you.workoutservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createdById;
    private String name;
    private String type;

    @OneToMany(mappedBy = "workout", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\": ").append(id).append(",");
        sb.append("\"createdById\": ").append(createdById).append(",");
        sb.append("\"name\": \"").append(name).append("\",");
        sb.append("\"type\": \"").append(type).append("\",");
        sb.append("\"exercises\": [");
        if (exercises != null) {
            for (int i = 0; i < exercises.size(); i++) {
                sb.append(exercises.get(i).toString());
                if (i < exercises.size() - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        sb.append("}");

        return sb.toString();
    }
}