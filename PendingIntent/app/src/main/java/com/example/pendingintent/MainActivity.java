package com.example.pendingintent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear un intent explícito para el BroadcastReceiver
        Intent intent = new Intent(this, BatteryReceiver.class);

        // Crear el PendingIntent para el Broadcast
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Configurar el AlarmManager para enviar el PendingIntent en intervalos
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            // Establecer un temporizador para ejecutar el PendingIntent cada minuto
            long triggerTime = System.currentTimeMillis();
            long interval = 60000; // 1 minuto

            // Utilizamos setRepeating para disparar el PendingIntent de manera repetida
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime, interval, pendingIntent);

            // Imprimir en la consola que se registró correctamente
            Log.d("BatteryReceiver", "PendingIntent registered successfully with AlarmManager");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Cancelar el PendingIntent cuando la app no está en primer plano
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        Log.d("BatteryReceiver", "PendingIntent unregistered successfully");
    }
}