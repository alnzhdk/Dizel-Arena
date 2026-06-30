package com.dieselarena.sportapp.ui.sportsman.news;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.News;
import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private AppDatabase database;
    private LiveData<List<News>> news;

    public NewsViewModel(Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        news = database.newsDao().getAllNews();
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}