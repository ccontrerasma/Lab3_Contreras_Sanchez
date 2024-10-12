package com.example.lab3_broadcastreceiver;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
public class MainActivity extends AppCompatActivity {

    private BatteryReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciar el BatteryReceiver
        batteryReceiver = new BatteryReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Registrar el BatteryReceiver
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, intentFilter);

        // Imprimir en la consola que se registró correctamente
        Log.d("BatteryReceiver", "BroadcastReceiver registered successfully");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Desregistrar el BatteryReceiver
        unregisterReceiver(batteryReceiver);

        // Imprimir en la consola que se desregistró correctamente
        Log.d("BatteryReceiver", "BroadcastReceiver unregistered successfully");
    }
}