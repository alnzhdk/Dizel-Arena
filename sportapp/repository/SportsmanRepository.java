package com.dieselarena.sportapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.User;
import java.util.List;

public class SportsmanRepository {
    private AppDatabase database;

    public SportsmanRepository(Application application) {
        database = AppDatabase.getInstance(application);
    }

    public LiveData<List<User>> getAllSportsmen() {
        return database.userDao().getAllSportsmen();
    }

    public LiveData<User> getSportsmanById(int userId) {
        return database.userDao().getUserById(userId);
    }
}