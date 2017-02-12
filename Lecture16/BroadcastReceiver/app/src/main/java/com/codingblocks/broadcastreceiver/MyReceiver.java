package com.codingblocks.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by championswimmer on 10/02/17.
 */

public class MyReceiver extends BroadcastReceiver {

    public static final String TAG = "BR";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive:  received broadcast");

        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            Toast.makeText(context, "BATTERY LEVEL CHANGED" + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1),
                    Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(context, "POWER CHANGED", Toast.LENGTH_SHORT).show();

            if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(i);
            }
        }

    }
}
