package com.dieselarena.sportapp.repository;

import android.app.Application;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.User;
import java.security.MessageDigest;

public class AuthRepository {
    private AppDatabase database;

    public AuthRepository(Application application) {
        database = AppDatabase.getInstance(application);
    }

    public User login(String username, String password) {
        String passwordHash = hashPassword(password);
        return database.userDao().login(username, passwordHash);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }
}