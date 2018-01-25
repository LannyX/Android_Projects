package edu.depaul.csc472.lanny.xul_things_to_do_in_beijing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingsActivity extends Activity {
    private static final String TAG = "RatingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Intent intent = getIntent();
        if (intent != null) {
            TextView name = findViewById(R.id.text1);
            TextView description = findViewById(R.id.text2);
            ImageView icon = findViewById(R.id.image);
            RatingBar rating = findViewById(R.id.rating);

            name.setText(intent.getCharSequenceExtra("ThingsName"));
            description.setText(intent.getCharSequenceExtra("ThingsDescription"));
            icon.setImageResource(intent.getIntExtra("ThingsIcon", -1));
            rating.setRating(intent.getFloatExtra("ThingsRating", 4));
        }
    }
    @Override
    public void finish() {
        Log.d(TAG, "finish()");

        Intent ratingResult = new Intent();
        RatingBar ratingBar = findViewById(R.id.rating);
        ratingResult.putExtra("ThingsRating", ratingBar.getRating());
        setResult(RESULT_OK, ratingResult);
        super.finish();
    }

}
