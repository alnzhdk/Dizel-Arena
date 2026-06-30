package com.dieselarena.sportapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import com.dieselarena.sportapp.data.AppDatabase;
import com.dieselarena.sportapp.data.entity.*;
import java.security.MessageDigest;
import java.util.concurrent.Executors;

public class SportApp extends Application {
    public static final String CHANNEL_ID = "training_reminder";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        insertTestData();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Напоминания о тренировках",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Уведомления о предстоящих тренировках");
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private void insertTestData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(SportApp.this);

            if (db.userDao().countUsers() > 0) {
                Log.d("DB", "Данные уже есть");
                return;
            }

            Log.d("DB", "Вставка данных...");

            String trainerPass = hashPassword("trainer123");
            String sportPass = hashPassword("sport123");

            // группы
            Group g1 = new Group("Начинающие");
            Group g2 = new Group("Продвинутые");
            long g1Id = db.groupDao().insertAndGetId(g1);
            long g2Id = db.groupDao().insertAndGetId(g2);

            // тренер без группы
            db.userDao().insert(new User("Иван Петрович", "trainer", "+79001234567", trainerPass, 0));

            // спортсмены
            db.userDao().insert(new User("Алексей Смирнов", "sportsman", "+79001112233", sportPass, (int) g1Id));
            db.userDao().insert(new User("Дмитрий Волков", "sportsman", "+79002223344", sportPass, (int) g2Id));
            db.userDao().insert(new User("Сергей Морозов", "sportsman", "+79003334455", sportPass, (int) g1Id));

            // получаем id
            User alexey = db.userDao().findByLogin("Алексей Смирнов");
            User dmitry = db.userDao().findByLogin("Дмитрий Волков");
            User sergey = db.userDao().findByLogin("Сергей Морозов");

            // новости
            db.newsDao().insert(new News("Открытие сезона", "Новый спортивный сезон начинается с 1 сентября", "2024-09-01"));
            db.newsDao().insert(new News("Соревнования", "Приглашаем всех на соревнования 15 октября", "2024-10-01"));
            db.newsDao().insert(new News("Ремонт зала", "Зал будет закрыт на ремонт с 20 по 25 ноября", "2024-11-15"));

            // упражнения
            db.exerciseDao().insert(new Exercise("Жим лежа", 80, 3, 0));
            db.exerciseDao().insert(new Exercise("Приседания", 100, 4, 0));
            db.exerciseDao().insert(new Exercise("Становая тяга", 120, 3, 0));
            db.exerciseDao().insert(new Exercise("Беговая дорожка", 0, 0, 20));

            // тренировки
            if (alexey != null) {
                db.trainingDao().insert(new Training("2024-09-15", "10:00", "completed", alexey.getId()));
                db.trainingDao().insert(new Training("2024-09-16", "12:00", "scheduled", alexey.getId()));
                db.trainingDao().insert(new Training("2024-09-20", "14:00", "scheduled", alexey.getId()));
            }
            if (dmitry != null) {
                db.trainingDao().insert(new Training("2024-09-15", "10:00", "completed", dmitry.getId()));
                db.trainingDao().insert(new Training("2024-09-17", "16:00", "missed", dmitry.getId()));
            }
            if (sergey != null) {
                db.trainingDao().insert(new Training("2024-09-18", "09:00", "scheduled", sergey.getId()));
            }

            Log.d("DB", "Готово!");
        });
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