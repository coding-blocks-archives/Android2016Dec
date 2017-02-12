package com.codingblocks.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyReceiver receiver;
    BroadcastReceiver receiver2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        receiver2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                    tv.setText("POWER CONNECTED");
                }
                if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                    tv.setText("POWER DISCONNECTED");
                }
            }
        };

        receiver = new MyReceiver();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        IntentFilter iFilter2 = new IntentFilter();
        iFilter2.addAction(Intent.ACTION_POWER_CONNECTED);
        iFilter2.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(receiver, iFilter);
        registerReceiver(receiver2, iFilter2);

    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver2);
        super.onPause();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
    }
}
