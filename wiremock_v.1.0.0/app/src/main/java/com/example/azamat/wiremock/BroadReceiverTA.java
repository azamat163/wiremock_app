package com.example.azamat.wiremock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadReceiverTA extends BroadcastReceiver {
    private static String LOG_TAG = "BroadReceiver";
    public BroadReceiverTA() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(LOG_TAG,"onReceive" + intent.getAction());
        Intent serviceIntent = new Intent(context, IntentServiceTA.class);
        context.startService(serviceIntent);

    }
}
