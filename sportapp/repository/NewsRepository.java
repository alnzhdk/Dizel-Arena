package com.dieselarena.sportapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.News;
import java.util.List;

public class NewsRepository {
    private AppDatabase database;

    public NewsRepository(Application application) {
        database = AppDatabase.getInstance(application);
    }

    public LiveData<List<News>> getAllNews() {
        return database.newsDao().getAllNews();
    }
}