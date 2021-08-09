package algonquin.cst2335.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.algonquin.cst2335.finalproject.R;

import algonquin.cst2335.finalproject.JJ_OCTranspoActivity;
import algonquin.cst2335.finalproject.bz_MainActivity;
import algonquin.cst2335.finalproject.sv_MainActivity;
import algonquin.cst2335.finalproject.*;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ocTranspo = findViewById(R.id.octranspo);
        Button carCharge = findViewById(R.id.carCharger);
        Button movie = findViewById(R.id.movieFinder);
        Button soccer = findViewById(R.id.soccerGames);

        ocTranspo.setOnClickListener(( clk) -> {
            Intent intent = new Intent(this, JJ_OCTranspoActivity.class);
            startActivity(intent);
        });

        carCharge.setOnClickListener( (clk) -> {
            Intent intent = new Intent(this, SS_MainActivity.class);
            startActivity(intent);
        });

        movie.setOnClickListener( (clk) -> {
            Intent intent = new Intent(this, sv_MainActivity.class);
            startActivity(intent);
        });

        soccer.setOnClickListener( (clk) -> {
            Intent intent = new Intent(this, bz_MainActivity.class);
            startActivity(intent);
        });
    }
}