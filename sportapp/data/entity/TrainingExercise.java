package com.dieselarena.sportapp.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "training_exercises",
        primaryKeys = {"training_id", "exercise_id"},
        foreignKeys = {
                @ForeignKey(entity = Training.class,
                        parentColumns = "id",
                        childColumns = "training_id"),
                @ForeignKey(entity = Exercise.class,
                        parentColumns = "id",
                        childColumns = "exercise_id")
        },
        indices = {@Index("exercise_id")})
public class TrainingExercise {
    @ColumnInfo(name = "training_id")
    private int trainingId;

    @ColumnInfo(name = "exercise_id")
    private int exerciseId;

    @ColumnInfo(name = "actual_weight")
    private float actualWeight;

    @ColumnInfo(name = "actual_sets")
    private int actualSets;

    @ColumnInfo(name = "actual_time")
    private int actualTime;

    public TrainingExercise(int trainingId, int exerciseId) {
        this.trainingId = trainingId;
        this.exerciseId = exerciseId;
    }

    // геттеры и сеттеры
    public int getTrainingId() { return trainingId; }
    public void setTrainingId(int trainingId) { this.trainingId = trainingId; }

    public int getExerciseId() { return exerciseId; }
    public void setExerciseId(int exerciseId) { this.exerciseId = exerciseId; }

    public float getActualWeight() { return actualWeight; }
    public void setActualWeight(float actualWeight) { this.actualWeight = actualWeight; }

    public int getActualSets() { return actualSets; }
    public void setActualSets(int actualSets) { this.actualSets = actualSets; }

    public int getActualTime() { return actualTime; }
    public void setActualTime(int actualTime) { this.actualTime = actualTime; }
}