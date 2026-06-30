package com.dieselarena.sportapp.ui.sportsman.profile;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.User;

public class ProfileViewModel extends AndroidViewModel {
    private AppDatabase database;

    public ProfileViewModel(Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
    }

    public LiveData<User> getUser(int userId) {
        return database.userDao().getUserById(userId);
    }

    public void updatePhoto(int userId, String photoUri) {
        new Thread(() -> {
            database.userDao().updatePhoto(userId, photoUri);
        }).start();
    }
}