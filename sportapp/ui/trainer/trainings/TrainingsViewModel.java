package com.dieselarena.sportapp.ui.trainer.trainings;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.Training;
import com.dieselarena.sportapp.repository.TrainingRepository;
import java.util.ArrayList;
import java.util.List;

public class TrainingsViewModel extends AndroidViewModel {
    private TrainingRepository repository;
    private MutableLiveData<List<Training>> trainings;

    public TrainingsViewModel(Application application) {
        super(application);
        repository = new TrainingRepository(application);
        trainings = new MutableLiveData<>(new ArrayList<>());
        loadAllTrainings();
    }

    private void loadAllTrainings() {
        AppDatabase db = AppDatabase.getInstance(getApplication());
        new Thread(() -> {
            List<Training> allTrainings = new ArrayList<>();
            List<Training> t1 = db.trainingDao().getTrainingsBySportsman(2).getValue();
            List<Training> t2 = db.trainingDao().getTrainingsBySportsman(3).getValue();
            List<Training> t3 = db.trainingDao().getTrainingsBySportsman(4).getValue();
            if (t1 != null) allTrainings.addAll(t1);
            if (t2 != null) allTrainings.addAll(t2);
            if (t3 != null) allTrainings.addAll(t3);
            trainings.postValue(allTrainings);
        }).start();
    }

    public LiveData<List<Training>> getTrainings() {
        return trainings;
    }

    public void insertTraining(Training training) {
        repository.insertTraining(training);
        loadAllTrainings();
    }
}