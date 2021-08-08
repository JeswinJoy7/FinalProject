package algonquin.cst2335.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

/**
 * A bage that receives and stores the user's rating
 */
public class bz_RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bz_rating);

        Intent ratePage = new Intent(bz_RateActivity.this, bz_MainActivity.class);
        SeekBar bar = findViewById(R.id.seekBar);
        Button submit = findViewById(R.id.button3);


        submit.setOnClickListener(clk -> {


            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            int rating = bar.getProgress();
            editor.putInt("rating", rating);
            editor.apply();

            startActivity(ratePage);

        });

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        int rating = preferences.getInt("rating", 2);
        bar.setProgress(rating);

    }
}
