package com.codepath.apps.basictwitter.services;

import org.json.JSONArray;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetService extends IntentService {
    public static final String ACTION_HOME = "com.codepath.apps.basictwitter.services.TweetService.home";
    private TwitterClient client;

    public TweetService() {
        super("tweet-service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                client = TwitterApplication.getRestClient();

                client.getHomeTimeline(new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONArray json) {
                        Intent in = new Intent(ACTION_HOME);
                        // Put extras into the intent as usual
                        in.putExtra("resultValue", json.toString());
                        // Fire the broadcast with intent packaged
                        LocalBroadcastManager.getInstance(
                                getApplicationContext()).sendBroadcast(in);
                    }

                    @Override
                    public void onFailure(Throwable e, String s) {
                        // TODO Auto-generated method stub
                        // super.onFailure(statusCode, headers, responseString,
                        // throwable);
                        Log.d("debug", e.toString());
                        Log.d("debug", s.toString());
                    }
                });
            }
        });

    }
}
