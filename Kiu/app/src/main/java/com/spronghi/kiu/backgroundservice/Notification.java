package com.spronghi.kiu.backgroundservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.spronghi.kiu.R;
import com.spronghi.kiu.activity.MainActivity;
import com.spronghi.kiu.activity.RequestActivity;
import com.spronghi.kiu.request.RequestChecker;

/**
 * Created by spronghi on 13/09/16.
 */
public class Notification {
    public static final String ARRIVED = "request";
    public static final String ACCEPT = "accept";
    public static final String REFUSE = "refuse";

    private static NotificationCompat.Builder builder;
    private static NotificationManager mNotificationManager;
    public static void sendNotification(Context context, String flag){
        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_logo);

        switch (flag){
            case ARRIVED:
                builder.setContentTitle(context.getString(R.string.arrived_request));
            case ACCEPT:
                builder.setContentTitle(context.getString(R.string.accepted_request));
            case REFUSE:
                builder.setContentTitle(context.getString(R.string.refused_request));
        }

        builder.setContentText(context.getString(R.string.request_body));

        Intent resultIntent = new Intent(context, RequestActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(RequestActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = 1;
        builder.setAutoCancel(true);
        mNotificationManager.notify(notificationID, builder.build());

    }
}
