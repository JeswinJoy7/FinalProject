package algonquin.cst2335.finalproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView busRouteList;
    private String stringURL;
    private EditText stationNo;
    private Button searchBtn;
    ArrayList<BusRoutes> messages = new ArrayList<>();
    BusRouteAdapter adt = new BusRouteAdapter();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stationNo = findViewById(R.id.stationEditText);
        searchBtn = findViewById(R.id.searchButton);
        String StNo = stationNo.getText().toString();

        // the recyclerview
        busRouteList = findViewById(R.id.busRoutesList);
        busRouteList.setAdapter(adt);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        busRouteList.setLayoutManager(layoutManager);

        // Shared Preferences
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String savedStationNo = prefs.getString("StationNo", "");
        stationNo.setText(savedStationNo);



        // setting the JSON
        searchBtn.setOnClickListener( (click) -> {

            // Shared Preference
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("StationNo", stationNo.getText().toString());
            editor.apply();

            //Toast Message
            Toast.makeText(getApplicationContext(),"Searching for Avaialable Buses in " + stationNo.getText().toString(), Toast.LENGTH_LONG).show();

            Executor newThread = Executors.newSingleThreadExecutor();
            newThread.execute( () -> {
                try {
                    String stationNumber = stationNo.getText().toString();
                    stringURL = "https://api.octranspo1.com/v2.0/GetRouteSummaryForStop?appID=223eb5c3&&apiKey=ab27db5b435b8c8819ffb8095328e775&stopNo="
                                + URLEncoder.encode(stationNumber, "UTF-8");
                    URL url = new URL(stringURL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream( urlConnection.getInputStream());

                    String text = (new BufferedReader(
                                    new InputStreamReader(in, StandardCharsets.UTF_8)))
                                    .lines()
                                    .collect(Collectors.joining("\n"));

                    JSONObject theDocument = new JSONObject( text);

                    JSONObject getRouteSummaryForStopResults = theDocument.getJSONObject("GetRouteSummaryForStopResult");
                    String stopNo = getRouteSummaryForStopResults.getString("StopNo");
                    String stopDescription = getRouteSummaryForStopResults.getString("StopDescription");

                    JSONObject routes = getRouteSummaryForStopResults.getJSONObject("Routes");
                    JSONArray route = routes.getJSONArray("Route");

                    for(int i =0; i< route.length(); i++) {
                        JSONObject busRoutes = route.getJSONObject(i);

                        String routeNo = busRoutes.getString("RouteNo");
                        String routeHeading = busRoutes.getString("RouteHeading");
                        BusRoutes nextBusRoutes = new BusRoutes(routeNo, routeHeading);
                        messages.add(nextBusRoutes);
                    }

                    runOnUiThread( () -> {
                        TextView busSt = findViewById(R.id.busStationName);
                        busSt.setText(stopDescription);
                        busSt.setVisibility(View.VISIBLE);

                        busSt = findViewById(R.id.busStationNo);
                        busSt.setText(stopNo);
                        busSt.setVisibility(View.VISIBLE);
                    });
                }
                catch (IOException | JSONException ioe) {
                    Log.e("Connection error",ioe.getMessage());
                }
            });

        });
    }

    /** for rows in the recyclerview */
    private class RowsViews extends RecyclerView.ViewHolder {

        TextView busNum;
        TextView busName;
        int position = 0;

        public RowsViews(View itemView) {
            super(itemView);

            // Alert Dialog box and
            itemView.setOnClickListener( click -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Question:")
                                .setMessage("Do you want to delete the Message: " + busNum.getText() + " " + busName.getText())
                                .setNegativeButton("No", (dialog, cl) -> {
                                })
                                .setPositiveButton("Yes", (dialog, cl) -> {

                                    BusRoutes removedMessage = messages.get(position);
                                    messages.remove(position);
                                    adt.notifyItemRemoved(position);

                                    //SnackBar
                                    Snackbar.make(busName, "You deleted message #"+position,Snackbar.LENGTH_LONG)
                                            .setAction("Undo", clk -> {
                                                messages.add(position, removedMessage);
                                                adt.notifyItemInserted(position);
                                            })
                                            .show();
                                }).create().show();

                itemView.setOnClickListener( (clk) -> {
                    Intent intent = new Intent(itemView.getContext(), BusRouteDetails.class);
                    intent.putExtra("BusNum" , busNum.getText().toString());
                    intent.putExtra("BusName", busName.getText().toString());
                    startActivity(intent);

                    });
                });

            busNum = itemView.findViewById(R.id.busNumber);
            busName = itemView.findViewById(R.id.busName);
        }

        public void setPosition(int p) {
                position = p;
            }
    }

    // Adapter
    private class BusRouteAdapter extends RecyclerView.Adapter<RowsViews>{

        @Override
        public RowsViews onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View loadedRow = inflater.inflate(R.layout.avaiable_busroutes, parent, false);
            return new RowsViews(loadedRow);
            //return new RowsViews(getLayoutInflater().inflate(R.layout.avaiable_busroutes, parent,false));
        }

        @Override
        public void onBindViewHolder(RowsViews holder, int position) {
            holder.busNum.setText(messages.get(position).getBusNumber());
            holder.busName.setText(messages.get(position).getBusName());
            holder.setPosition(position);

//            holder.itemView.setOnClickListener( (clk) -> {
//                Intent intent = new Intent(holder.itemView.getContext(), BusRouteDetails.class);
//                intent.putExtra("BusNum" , holder.busNum.getText().toString());
//                intent.putExtra("BusName", holder.busName.getText().toString());
//                startActivity(intent);
//
//            });
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }
    }

    /** Class Bus Routes */
    private class BusRoutes
    {
        String busNumber;
        String busName;

        public BusRoutes(String busNumber, String busName) {
            this.busNumber = busNumber;
            this.busName = busName;
        }

        public String getBusNumber() {
            return busNumber;
        }
        public String getBusName() {
            return busName;
        }
    }

}