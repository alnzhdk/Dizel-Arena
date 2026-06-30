package com.dieselarena.sportapp.ui.trainer.sportsmen;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.User;
import java.util.List;

public class SportsmenViewModel extends AndroidViewModel {
    private AppDatabase database;
    private LiveData<List<User>> sportsmen;

    public SportsmenViewModel(Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        sportsmen = database.userDao().getAllSportsmen();
    }

    public LiveData<List<User>> getSportsmen() {
        return sportsmen;
    }
}