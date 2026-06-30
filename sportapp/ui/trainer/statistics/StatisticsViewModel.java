package com.dieselarena.sportapp.ui.trainer.statistics;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.dieselarena.sportapp.data.AppDatabase;

public class StatisticsViewModel extends AndroidViewModel {
    private MutableLiveData<TrainingStats> stats;

    public StatisticsViewModel(Application application) {
        super(application);
        stats = new MutableLiveData<>();
        loadStats();
    }

    private void loadStats() {
        AppDatabase db = AppDatabase.getInstance(getApplication());
        new Thread(() -> {
            int completed = db.trainingDao().getTrainingCountByStatus(1, "completed", "2024-09-01", "2024-09-30");
            int missed = db.trainingDao().getTrainingCountByStatus(1, "missed", "2024-09-01", "2024-09-30");
            int total = completed + missed;
            stats.postValue(new TrainingStats(completed, missed, total));
        }).start();
    }

    public MutableLiveData<TrainingStats> getStatistics() {
        return stats;
    }

    public static class TrainingStats {
        public int completed;
        public int missed;
        public int total;

        public TrainingStats(int completed, int missed, int total) {
            this.completed = completed;
            this.missed = missed;
            this.total = total;
        }
    }
}