package com.dieselarena.sportapp.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.dieselarena.sportapp.data.dao.*;
import com.dieselarena.sportapp.data.entity.*;

@Database(entities = {
        User.class,
        Group.class,
        Training.class,
        Exercise.class,
        TrainingExercise.class,
        News.class
}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract GroupDao groupDao();
    public abstract TrainingDao trainingDao();
    public abstract ExerciseDao exerciseDao();
    public abstract TrainingExerciseDao trainingExerciseDao();
    public abstract NewsDao newsDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "diesel_arena_db"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}