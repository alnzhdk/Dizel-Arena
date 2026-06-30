package com.dieselarena.sportapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.Training;
import com.dieselarena.sportapp.data.entity.TrainingExercise;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrainingRepository {
    private AppDatabase database;
    private ExecutorService executorService;

    public TrainingRepository(Application application) {
        database = AppDatabase.getInstance(application);
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Training>> getTrainingsBySportsman(int sportsmanId) {
        return database.trainingDao().getTrainingsBySportsman(sportsmanId);
    }

    public void insertTraining(Training training) {
        executorService.execute(() -> {
            database.trainingDao().insert(training);
        });
    }

    public void updateTrainingStatus(int trainingId, String status) {
        executorService.execute(() -> {
            // получаем тренировку, обновляем статус
        });
    }

    public LiveData<List<TrainingExercise>> getExercisesForTraining(int trainingId) {
        return database.trainingExerciseDao().getExercisesForTraining(trainingId);
    }

    public void saveExerciseResult(TrainingExercise exercise) {
        executorService.execute(() -> {
            database.trainingExerciseDao().update(exercise);
        });
    }
}