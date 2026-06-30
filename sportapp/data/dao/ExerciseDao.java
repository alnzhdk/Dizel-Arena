package com.dieselarena.sportapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.dieselarena.sportapp.data.entity.Exercise;
import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void insert(Exercise exercise);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getAllExercises();

    @Query("SELECT * FROM exercises WHERE id IN (SELECT exercise_id FROM training_exercises WHERE training_id = :trainingId)")
    LiveData<List<Exercise>> getExercisesByTraining(int trainingId);
}