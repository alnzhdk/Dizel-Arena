package com.dieselarena.sportapp.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "trainings",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "sportsman_id"),
        indices = {@Index("sportsman_id")})
public class Training {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private String date; // формат YYYY-MM-DD

    @ColumnInfo(name = "time")
    private String time; // формат HH:MM

    @ColumnInfo(name = "status")
    private String status; // "scheduled", "completed", "missed"

    @ColumnInfo(name = "sportsman_id")
    private int sportsmanId;

    public Training(String date, String time, String status, int sportsmanId) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.sportsmanId = sportsmanId;
    }

    // геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getSportsmanId() { return sportsmanId; }
    public void setSportsmanId(int sportsmanId) { this.sportsmanId = sportsmanId; }
}