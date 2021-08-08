package algonquin.cst2335.finalproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

                for(int n = 0; n <RouteDirection.length(); n++) {

                    JSONObject p = RouteDirection.getJSONObject(n);
                    String RouteLabel = p.getString("RouteLabel");
                    JSONObject Trips = p.getJSONObject("Trips");
                    JSONArray Trip = Trips.getJSONArray("Trip");

                //for(int i = 0; i< Trip.length(); i++) {
                    JSONObject position = Trip.getJSONObject(0);
                    String Longitude = position.getString("Longitude");
                    String Latitude = position.getString("Latitude");
                    String GPSSpeed = position.getString("GPSSpeed");
                    String TripDestination = position.getString("TripDestination");
                    String TripStartTime = position.getString("TripStartTime");
                    String AdjustedScheduleTime = position.getString("AdjustedScheduleTime");

                    runOnUiThread(() -> {

                        if(RouteLabel.equals(busName)) {
                            TextView dest = findViewById(R.id.destination);
                            dest.setText(TripDestination);
                            dest.setVisibility(View.VISIBLE);

                            TextView latitude = findViewById(R.id.latitude);
                            latitude.setText(Latitude);
                            latitude.setVisibility(View.VISIBLE);

                            TextView longitude = findViewById(R.id.longitude);
                            longitude.setText(Longitude);
                            longitude.setVisibility(View.VISIBLE);

                            TextView gps = findViewById(R.id.speed);
                            gps.setText(GPSSpeed);
                            gps.setVisibility(View.VISIBLE);

                            TextView startTime = findViewById(R.id.startTime);
                            startTime.setText(TripStartTime);
                            startTime.setVisibility(View.VISIBLE);

                            TextView adjustedTime = findViewById(R.id.adjustedTime);
                            adjustedTime.setText(AdjustedScheduleTime);
                            adjustedTime.setVisibility(View.VISIBLE);
                        }
                    });
                } //}
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
