package com.codingblocks.pushmsg;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by championswimmer on 19/02/17.
 */

public class MyMsgService extends FirebaseMessagingService {

    public static final String TAG = "MSG";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: ");

        String msg = "";

        if (remoteMessage.getNotification() != null) {
            msg = remoteMessage.getNotification().getBody();
        } if (remoteMessage.getData() != null) {
            msg = remoteMessage.getData().get("message");
        }

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this);
        notifBuilder.setContentText("Some content")
                .setColor(Color.BLUE)
                .setSubText(msg)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);

        PendingIntent pi = PendingIntent.getActivity(this,
                123,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        notifBuilder.setContentIntent(pi);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(234, notifBuilder.build());
    }


}
