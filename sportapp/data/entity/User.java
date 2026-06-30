package com.dieselarena.sportapp.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "role")
    private String role; // "sportsman" или "trainer"

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "photo_uri")
    private String photoUri;

    @ColumnInfo(name = "password_hash")
    private String passwordHash;

    @ColumnInfo(name = "group_id")
    private int groupId;

    public User(String name, String role, String phone, String passwordHash, int groupId) {
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.groupId = groupId;
    }

    // геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPhotoUri() { return photoUri; }
    public void setPhotoUri(String photoUri) { this.photoUri = photoUri; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public int getGroupId() { return groupId; }
    public void setRole(int groupId) { this.groupId = groupId; }
}
