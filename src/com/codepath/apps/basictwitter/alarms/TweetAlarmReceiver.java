package com.codepath.apps.basictwitter.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.codepath.apps.basictwitter.services.TweetService;

public class TweetAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.codepath.apps.basictwitter.alarms.TweetAlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, TweetService.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }

}