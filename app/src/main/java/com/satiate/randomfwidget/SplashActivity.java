package com.satiate.randomfwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tooleap.sdk.TooleapActivities;
import com.tooleap.sdk.TooleapPopOutMiniApp;

/**
 * Created by Rishabh Bhatia on 27/10/16.
 */

public class SplashActivity extends TooleapActivities.Activity {

    private static final int KEY_CODE = 199;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getApplicationContext();
        ComponentName name = new ComponentName(context, ThrowRandomFs.class);
        int [] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(name);

        if(ids.length != 0)
        {
            Intent pickIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
            pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, ids[0]);
            startActivityForResult(pickIntent, KEY_CODE);
        }

        finish();

       /* Intent intent = new Intent(SplashActivity.this, SplashActivity.class);
        TooleapPopOutMiniApp miniApp = new TooleapPopOutMiniApp(SplashActivity.this, intent);
        miniApp.contentTitle = "My First Mini App";
        miniApp.notificationText = "Hello! I'm the Tooleap bubble";
        miniApp.bubbleBackgroundColor = 0x78FFFFFF;*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == KEY_CODE && resultCode == Activity.RESULT_OK)
        {
            Log.d("rishabh","paza palat gaya");
        }
    }
}
