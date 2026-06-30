package com.dieselarena.sportapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.dieselarena.sportapp.data.entity.News;
import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    void insert(News news);

    @Query("SELECT * FROM news ORDER BY date DESC")
    LiveData<List<News>> getAllNews();
}