package com.dieselarena.sportapp.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // перезапуск напоминаний после перезагрузки
            // в реальном приложении здесь восстанавливаются все активные напоминания из БД
        }
    }
}