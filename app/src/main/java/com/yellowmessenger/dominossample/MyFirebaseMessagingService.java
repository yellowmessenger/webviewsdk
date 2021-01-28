package com.yellowmessenger.dominossample;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ymwebview.YMBotPlugin;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        YMBotPlugin pluginYM =  YMBotPlugin.getInstance();
        Log.d("Notification", "got message: "+ remoteMessage.getNotification().getBody());
    }



    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onNewToken(String token) {
        Log.d("Notification", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
    }

}
