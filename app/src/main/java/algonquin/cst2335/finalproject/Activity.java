package algonquin.cst2335.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Activity extends AppCompatActivity {

    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener((clk)->{
            Intent intent = new Intent(this, OCTranspoActivity.class);
            startActivity(intent);
        });
    }
}
