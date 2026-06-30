package com.dieselarena.sportapp.ui.sportsman.schedule;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.dieselarena.sportapp.data.entity.Training;
import com.dieselarena.sportapp.repository.TrainingRepository;
import java.util.List;

public class ScheduleViewModel extends AndroidViewModel {
    private TrainingRepository repository;
    private LiveData<List<Training>> trainings;

    public ScheduleViewModel(Application application) {
        super(application);
        repository = new TrainingRepository(application);
    }

    public void init(int sportsmanId) {
        trainings = repository.getTrainingsBySportsman(sportsmanId);
    }

    public LiveData<List<Training>> getTrainings() {
        return trainings;
    }
}