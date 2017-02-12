package com.codingblocks.broadcasts;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver bReceiver;
    IntentFilter iFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvBattPer = (TextView) findViewById(R.id.battPer);
        

        bReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {

                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                    int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);

                    tvBattPer.setText(String.valueOf(((float) level/ (float) scale) * 100));

                }
            }
        };

        iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(bReceiver, iFilter);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(bReceiver, iFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(bReceiver);
        super.onPause();
    }
}
