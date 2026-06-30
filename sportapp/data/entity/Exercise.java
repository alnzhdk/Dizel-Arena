package com.dieselarena.sportapp.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @ColumnInfo(name = "norm_weight")
    private float normWeight;

    @ColumnInfo(name = "norm_sets")
    private int normSets;

    @ColumnInfo(name = "norm_time")
    private int normTime; // в минутах

    public Exercise(String name, float normWeight, int normSets, int normTime) {
        this.name = name;
        this.normWeight = normWeight;
        this.normSets = normSets;
        this.normTime = normTime;
    }

    // геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getNormWeight() { return normWeight; }
    public void setNormWeight(float normWeight) { this.normWeight = normWeight; }

    public int getNormSets() { return normSets; }
    public void setNormSets(int normSets) { this.normSets = normSets; }

    public int getNormTime() { return normTime; }
    public void setNormTime(int normTime) { this.normTime = normTime; }
}