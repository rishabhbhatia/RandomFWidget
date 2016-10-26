package com.satiate.randomfwidget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

/**
 * Created by Rishabh Bhatia on 27/10/16.
 */

public class ThrowRandomFs extends AppWidgetProvider {

    private static final String SYNC_CLICKED = "ACTION_CLICK";


    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d("rishabh", " on enabled");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d("rishabh", " on disabled");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d("rishabh", " on receive");

        if (SYNC_CLICKED.equals(intent.getAction())) {
            Log.d("rishabh","its a valid click");
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        Log.d("rishabh", " on update");
        RemoteViews remoteViews;
        ComponentName watchWidget;

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_random_fs);
        watchWidget = new ComponentName(context, ThrowRandomFs.class);

        remoteViews.setOnClickPendingIntent(R.id.iv_throw_random_f, getPendingSelfIntent(context, SYNC_CLICKED));
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public static class UpdateWidgetService extends IntentService {
        public UpdateWidgetService() {
            // only for debug purpose
            super("UpdateWidgetService");

        }

        @Override
        protected void onHandleIntent(Intent intent) {
            AppWidgetManager appWidgetManager = AppWidgetManager
                    .getInstance(UpdateWidgetService.this);

            int incomingAppWidgetId = intent.getIntExtra(EXTRA_APPWIDGET_ID,
                    INVALID_APPWIDGET_ID);

            if (incomingAppWidgetId != INVALID_APPWIDGET_ID) {
                try {
                    updateRandomFWidget(appWidgetManager, incomingAppWidgetId,
                            intent);
                } catch (NullPointerException e) {
                }

            }

        }

        public void updateRandomFWidget(AppWidgetManager appWidgetManager,
                                        int appWidgetId, Intent intent) {
            Log.v("rishabh", this.getPackageName());
            RemoteViews views = new RemoteViews(this.getPackageName(),
                    R.layout.widget_random_fs);

            views.setOnClickPendingIntent(R.id.iv_throw_random_f, getPendingSelfIntent(UpdateWidgetService.this, SYNC_CLICKED));

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

        protected PendingIntent getPendingSelfIntent(Context context, String action) {
            Intent intent = new Intent(context, getClass());
            intent.setAction(action);
            return PendingIntent.getBroadcast(context, 0, intent, 0);
        }
    }
}

