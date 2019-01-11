package com.example.ayana.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (intentAction != null) {
            String message = "Unknown intent action";
            int randomNumber = intent.getIntExtra("RandomNumber", 0);
            switch (intentAction) {
                case Intent.ACTION_HEADSET_PLUG:
                    message = "Play music!";
                    break;
//                case Intent.ACTION_POWER_DISCONNECTED:
//                    message = "POWER DISCONNECTED!";
//                    break;
                case ACTION_CUSTOM_BROADCAST:
                    message = "Custom Broadcast Received!\nsquare of the random number: "
                            + randomNumber;
                    break;
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}

