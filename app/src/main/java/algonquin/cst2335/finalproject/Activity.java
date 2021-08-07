package algonquin.cst2335.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity extends AppCompatActivity {

    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        backBtn = findViewById(R.id.detailsBtn);
        backBtn.setOnClickListener((clk)->{
            Intent intent = new Intent(this, OCTranspoActivity.class);
            startActivity(intent);
        });
    }
}
