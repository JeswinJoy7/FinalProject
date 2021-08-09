package algonquin.cst2335.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.algonquin.cst2335.finalproject.R;

import androidx.appcompat.app.AppCompatActivity;

public class JJ_Activity extends AppCompatActivity {

    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jj_activity_contents);

        backBtn = findViewById(R.id.detailsBtn);
        backBtn.setOnClickListener((clk)->{
            Intent intent = new Intent(this, JJ_OCTranspoActivity.class);
            startActivity(intent);
        });
    }
}
