package algonquin.cst2335.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.algonquin.cst2335.finalproject.R;
import com.google.android.material.snackbar.Snackbar;

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
        EditText bz_ed = findViewById(R.id.bz_U_name);


        submit.setOnClickListener(clk -> {


            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            int rating = bar.getProgress();
            editor.putInt("rating", rating);
            editor.putString("uname", bz_ed.getText().toString());
            editor.apply();

            Snackbar.make(submit, "You rated our app\n Thank you)", Snackbar.LENGTH_LONG).show();


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(ratePage);

        });

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        int rating = preferences.getInt("rating", 2);
        bz_ed.setText(preferences.getString("uname", ""));
        bar.setProgress(rating);

    }
}
