package com.codingblocks.alarmmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final int PI_CODE = 3412;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PendingIntent pi = PendingIntent.getActivity(this,
                PI_CODE,
                (new Intent(this, MainActivity.class))
                        .addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                0
                );

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        am.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + (60 * 1000),
                (60*1000*5),
                pi
        );
    }
}
