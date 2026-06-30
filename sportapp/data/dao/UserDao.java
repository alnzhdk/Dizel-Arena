package com.dieselarena.sportapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.dieselarena.sportapp.data.entity.User;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE name = :username AND password_hash = :passwordHash LIMIT 1")
    User login(String username, String passwordHash);

    @Query("SELECT * FROM users WHERE name = :username LIMIT 1")
    User findByLogin(String username);

    @Query("SELECT COUNT(*) FROM users")
    int countUsers();

    @Query("SELECT * FROM users WHERE role = 'sportsman' ORDER BY group_id")
    LiveData<List<User>> getAllSportsmen();

    @Query("SELECT * FROM users WHERE id = :userId")
    LiveData<User> getUserById(int userId);

    @Query("UPDATE users SET photo_uri = :photoUri WHERE id = :userId")
    void updatePhoto(int userId, String photoUri);
}