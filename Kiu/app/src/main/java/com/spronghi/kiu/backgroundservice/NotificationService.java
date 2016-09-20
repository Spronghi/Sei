package com.spronghi.kiu.backgroundservice;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.spronghi.kiu.R;
import com.spronghi.kiu.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/*
*   usage :
*   Intent s = new Intent(this, NotificationService.class);
*   startService(s);
*/

public class NotificationService extends Service {

    private Timer timer = new Timer();

    public void onCreate() {
        super.onCreate();

        startservice();
    }

    private void startservice() {
        System.out.println("Servizio Avviato MADAFFAKKA!");
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {
                System.out.println("Creo una notifica!");
//Do whatever you want to do every “INTERVAL"
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
                mBuilder.setSmallIcon(R.drawable.logo);
                mBuilder.setContentTitle("Notification Alert, Click Me!");
                mBuilder.setContentText("Hi, This is Android Notification Detail!");
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addParentStack(MainActivity.class);

// Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
                int notificationID = 00001;
                mNotificationManager.notify(notificationID, mBuilder.build());
            }
        }, 0, 10000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (timer != null){

            timer.cancel();

            onCreate();


        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}