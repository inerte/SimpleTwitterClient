package com.codepath.apps.basictwitter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.listeners.OnSwipeTouchListener;
import com.codepath.apps.basictwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for position
        Tweet tweet = getItem(position);
        // Find or inflate the template
        View v;
        if (convertView == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            v = inflator.inflate(R.layout.tweet_item, parent, false);
        } else {
            v = convertView;
        }
        // Find the views within template
        ImageView ivProfileImage = (ImageView) v
                .findViewById(R.id.ivProfileImage);
        TextView tvName = (TextView) v.findViewById(R.id.tvName);
        TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
        ivProfileImage.setImageResource(android.R.color.transparent);
        ImageLoader imageLoader = ImageLoader.getInstance();
        // Populate views with tweet data
        imageLoader.displayImage(tweet.getUser().getProfileImageUrl(),
                ivProfileImage);
        tvName.setText(tweet.getUser().getName());
        tvUserName.setText('@' + tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());

        final Context c = getContext();

        if (tweet.isOwn()) {
            v.setOnTouchListener(new OnSwipeTouchListener(c) {
                @Override
                public void onSwipeRight() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);

                    builder.setMessage("Delete this tweet?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            Toast.makeText(c,
                                                    "Tweet will be removed!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        } else {
            v.setOnTouchListener(new OnSwipeTouchListener(c) {
                @Override
                public void onSwipeRight() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);

                    builder.setMessage("Retweet?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            Toast.makeText(c,
                                                    "Retweet in 3 2 1!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }

        return v;
    }
}
