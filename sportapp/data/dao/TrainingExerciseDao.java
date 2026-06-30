package com.dieselarena.sportapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.dieselarena.sportapp.data.entity.TrainingExercise;
import java.util.List;

@Dao
public interface TrainingExerciseDao {
    @Insert
    void insert(TrainingExercise trainingExercise);

    @Update
    void update(TrainingExercise trainingExercise);

    @Query("SELECT * FROM training_exercises WHERE training_id = :trainingId")
    LiveData<List<TrainingExercise>> getExercisesForTraining(int trainingId);
}