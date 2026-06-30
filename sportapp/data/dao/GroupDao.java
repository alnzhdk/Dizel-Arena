package com.dieselarena.sportapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.dieselarena.sportapp.data.entity.Group;
import java.util.List;

@Dao
public interface GroupDao {
    @Insert
    void insert(Group group);

    @Query("SELECT * FROM groups ORDER BY name")
    LiveData<List<Group>> getAllGroups();

    @Query("SELECT * FROM groups WHERE id = :groupId")
    Group getGroupById(int groupId);

    @Insert
    long insertAndGetId(Group group);
}