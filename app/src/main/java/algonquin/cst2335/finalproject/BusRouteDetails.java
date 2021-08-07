package algonquin.cst2335.finalproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class BusRouteDetails extends AppCompatActivity {

    Button backBtn;
    TextView BusNumber;
    TextView BusStationName;

    private String stringURL;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busroute_details);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener((clk)->{
            Intent intent = new Intent(this, OCTranspoActivity.class);
            startActivity(intent);
        });

        Intent fromPrevious = getIntent();
        String busNum = fromPrevious.getStringExtra("BusNum");
        String busName = fromPrevious.getStringExtra("BusName");
        String busStationNumber = fromPrevious.getStringExtra("BusStationNumber");

//        EditText stationNum = findViewById(R.id.stationEditText);
//        String busStationNumber = stationNum.getText().toString();

        BusNumber = findViewById(R.id.busNumber);
        BusStationName = findViewById(R.id.busName);

        BusNumber.setText(busNum);
        BusStationName.setText(busName);

        Executor newThread = Executors.newSingleThreadExecutor();
        newThread.execute(() -> {
            try {
                stringURL = "https://api.octranspo1.com/v2.0/GetNextTripsForStop?appID=223eb5c3&&apiKey=ab27db5b435b8c8819ffb8095328e775&stopNo="
                            + URLEncoder.encode(busStationNumber, "UTF-8")
                            + "&routeNo=" + URLEncoder.encode(busNum, "UTF-8");

                URL url = new URL(stringURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                String text = (new BufferedReader(
                        new InputStreamReader(in, StandardCharsets.UTF_8)))
                        .lines()
                        .collect(Collectors.joining("\n"));

                JSONObject theDocument = new JSONObject( text);
                JSONObject getNextTripsForStopResults = theDocument.getJSONObject("GetNextTripsForStopResult");
                JSONObject Route = getNextTripsForStopResults.getJSONObject("Route");

                JSONArray RouteDirection = Route.getJSONArray("RouteDirection");


                    JSONObject position = RouteDirection.getJSONObject(0);
                    String RouteLabel = position.getString("RouteLabel");
                    JSONObject Trips = position.getJSONObject("Trips");
                    JSONObject Trip = Trips.getJSONObject("Trip");

                    String Longitude = Trip.getString("Longitude");
                    String Latitude = Trip.getString("Latitude");
                    String GPSSpeed = Trip.getString("GPSSpeed");
                    String TripDestination = Trip.getString("TripDestination");
                    String TripStartTime = Trip.getString("TripStartTime");
                    String AdjustedScheduleTime = Trip.getString("AdjustedScheduleTime");


                runOnUiThread( () ->{

                    if(RouteLabel == busName){
                        TextView dest = findViewById(R.id.destination);
                        dest.setText(TripDestination);

                        TextView latitude = findViewById(R.id.latitude);
                        latitude.setText(Latitude);

                        TextView longitude = findViewById(R.id.longitude);
                        longitude.setText(Longitude);

                        TextView gps = findViewById(R.id.speed);
                        gps.setText(GPSSpeed);

                        TextView startTime = findViewById(R.id.startTime);
                        startTime.setText(TripStartTime);

                        TextView adjustedTime = findViewById(R.id.adjustedTime);
                        adjustedTime.setText(AdjustedScheduleTime);
                    }
                    else {

                    }

                });



            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
