package com.satiate.randomfwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

public class RandomFsConfigActivity extends AppCompatActivity {


    int mAppWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_fs_config);

        setResult(RESULT_CANCELED);
        showAppWidget();
    }

    private void showAppWidget() {

        mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(EXTRA_APPWIDGET_ID,
                    INVALID_APPWIDGET_ID);

            AppWidgetProviderInfo providerInfo = AppWidgetManager.getInstance(
                    getBaseContext()).getAppWidgetInfo(mAppWidgetId);
            String appWidgetLabel = providerInfo.label;

            Intent startService = new Intent(RandomFsConfigActivity.this,
                    ThrowRandomFs.UpdateWidgetService.class);
            startService.putExtra(EXTRA_APPWIDGET_ID, mAppWidgetId);
            startService.setAction("FROM CONFIGURATION ACTIVITY");
            setResult(RESULT_OK, startService);
            startService(startService);

            finish();
        }
        if (mAppWidgetId == INVALID_APPWIDGET_ID) {
            Log.i("rishabh", "I am invalid");
            finish();
        }

    }



}
