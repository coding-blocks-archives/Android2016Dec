package com.codingblocks.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        PendingIntent pi = PendingIntent.getActivity(
                this,
                123,
                new Intent(this, AlarmActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                0
        );

        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.uptimeMillis() + 60 * 1000,
                pi);

        am.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 60*2000,
                5 * 60 * 1000,
                pi
        );
    }
}
