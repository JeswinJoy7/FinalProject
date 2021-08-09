package algonquin.cst2335.finalproject.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import algonquin.cst2335.finalproject.Common.SharedManager;
import algonquin.cst2335.finalproject.Database.CharginPointDatabase;
import algonquin.cst2335.finalproject.Database.Entity;
import com.algonquin.cst2335.finalproject.R;


public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";

    Intent intent;

    SharedManager sharedPrefManager;

    TextView tvTitle, tvlat, tvLong, tvtelephone;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvTitle = findViewById(R.id.title);
        tvlat = findViewById(R.id.lat);
        tvLong = findViewById(R.id.longitude);
        tvtelephone = findViewById(R.id.tel);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        intent = getIntent();

        sharedPrefManager = new SharedManager(getApplicationContext());
        CharginPointDatabase dbHelper = new CharginPointDatabase(getApplicationContext());


        String title = sharedPrefManager.getString("Title");
        String Latitude = sharedPrefManager.getString("Latitude");
        String Longitude = sharedPrefManager.getString("Longitude");
        String Address = sharedPrefManager.getString("Address");
        String Tel = sharedPrefManager.getString("Tel");
        String Telsecond = sharedPrefManager.getString("Telsecond");
        String Email = sharedPrefManager.getString("Email");

        tvTitle.setText(title);
        tvlat.setText(Latitude);
        tvLong.setText(Longitude);
        tvtelephone.setText(Tel);

        Log.d(TAG, "onCreate: " + sharedPrefManager.getString("Latitude"));


        button.setOnClickListener(v -> {
            Intent i = new
                    Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + Latitude + ",-" + Longitude));
            startActivity(i);

        });

        button2.setOnClickListener(v -> {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Entity.FeedChargingPoint.COLUMN_NAME_TITLE, title);
            values.put(Entity.FeedChargingPoint.COLUMN_NAME_TELEPHONE, Tel);
            values.put(Entity.FeedChargingPoint.COLUMN_NAME_LATITUDE, Latitude);
            values.put(Entity.FeedChargingPoint.COLUMN_NAME_LONGITUDE, Longitude);

            long newrow = db.insert(Entity.FeedChargingPoint.TABLE_NAME, null, values);

            if (newrow > 0) {
                Toast.makeText(DetailsActivity.this, "Data Saved in Database", Toast.LENGTH_SHORT).show();
            }
            Log.d(TAG, "onClick: " + newrow);

        });


    }
}