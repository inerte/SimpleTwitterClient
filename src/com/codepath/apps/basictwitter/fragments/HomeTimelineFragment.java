package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;
import org.json.JSONException;

import android.R;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.codepath.apps.basictwitter.models.Tweet;
import com.codepath.apps.basictwitter.services.TweetService;

public class HomeTimelineFragment extends TweetsListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register for the particular broadcast based on ACTION string
        IntentFilter filter = new IntentFilter(TweetService.ACTION_HOME);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                testReceiver, filter);
        // or `registerReceiver(testReceiver, filter)` for a normal broadcast
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregister the listener when the application is paused
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                testReceiver);
        // or `unregisterReceiver(testReceiver)` for a normal broadcast
    }

    // Define the callback for what to do when data is received
    private BroadcastReceiver testReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            JSONArray json;
            try {
                json = new JSONArray(intent.getStringExtra("resultValue"));

                addAll(Tweet.fromJSONArray(json));

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                        getActivity()).setSmallIcon(R.drawable.btn_radio)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

                NotificationManager mNotificationManager = (NotificationManager) getActivity()
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(1, mBuilder.build());

            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    };
}
