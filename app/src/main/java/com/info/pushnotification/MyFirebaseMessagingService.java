package com.info.pushnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull String refreshedToken) {
        super.onNewToken(refreshedToken);
        Log.e(TAG, "refreshedToken: " + refreshedToken);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "remoteMessage: " + remoteMessage.getData().size());
        Log.e(TAG, "remoteMessage: " + remoteMessage.getNotification().getTitle());
        showNotifications(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }


    private void showNotifications(String title, String msg) {
        NotificationCompat.Builder notify = new NotificationCompat.Builder(this,"MyNotifications")
                .setContentText(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_background);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1001 ,notify.build());

    }
}
