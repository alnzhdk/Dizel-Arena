package com.dieselarena.sportapp.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.SportApp;

public class TrainingReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String trainingInfo = intent.getStringExtra("training_info");
        showNotification(context, trainingInfo);
    }

    private void showNotification(Context context, String info) {
        Notification notification = new NotificationCompat.Builder(context, SportApp.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Напоминание о тренировке")
                .setContentText(info)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    public static void scheduleReminder(Context context, String date, String time) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, TrainingReminderReceiver.class);
        intent.putExtra("training_info", "Тренировка " + date + " в " + time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // установка будильника за 1 час до тренировки (упрощенно)
        long triggerTime = System.currentTimeMillis() + 60 * 60 * 1000; // через час
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
    }
}