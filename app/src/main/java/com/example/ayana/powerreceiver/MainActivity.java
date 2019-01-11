package com.example.ayana.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    final int random = new Random().nextInt(19) + 1;

    private CustomReceiver mReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView number = findViewById(R.id.number);
        number.setText(String.valueOf(random));

        // specify the types of intents a component can receive.
        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_POWER_CONNECTED);
//        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        // register the receiver using Activity context.
        this.registerReceiver(mReceiver, filter);

        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        // Send local broadcast message
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);

        customBroadcastIntent.putExtra("RandomNumber", random * random);

        LocalBroadcastManager
                .getInstance(this)
                .sendBroadcast(customBroadcastIntent);
    }
}