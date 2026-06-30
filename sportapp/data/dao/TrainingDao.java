package com.dieselarena.sportapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.dieselarena.sportapp.data.entity.Training;
import java.util.List;

@Dao
public interface TrainingDao {
    @Insert
    long insert(Training training);

    @Update
    void update(Training training);

    @Query("SELECT * FROM trainings WHERE sportsman_id = :sportsmanId ORDER BY date DESC, time DESC")
    LiveData<List<Training>> getTrainingsBySportsman(int sportsmanId);

    @Query("SELECT * FROM trainings WHERE date = :date AND sportsman_id = :sportsmanId")
    List<Training> getTrainingsByDate(String date, int sportsmanId);

    @Query("SELECT * FROM trainings WHERE sportsman_id = :sportsmanId AND status = 'completed' AND date BETWEEN :startDate AND :endDate")
    LiveData<List<Training>> getCompletedTrainings(int sportsmanId, String startDate, String endDate);

    @Query("SELECT COUNT(*) FROM trainings WHERE sportsman_id = :sportsmanId AND status = :status AND date BETWEEN :startDate AND :endDate")
    int getTrainingCountByStatus(int sportsmanId, String status, String startDate, String endDate);

    @Query("DELETE FROM trainings WHERE id = :trainingId")
    void deleteTraining(int trainingId);
}