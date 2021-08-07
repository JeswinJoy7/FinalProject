package algonquin.cst2335.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
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

public class OCTranspoActivity extends AppCompatActivity {


    private RecyclerView busRouteList;
    private String stringURL;
    private EditText stationNo;
    private Button searchBtn;
    ArrayList<BusRoutes> messages = new ArrayList<>();
    BusRouteAdapter adt = new BusRouteAdapter();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.infoView:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Instructions on how to use!")
                        .setMessage("To locate relevant bus routes, go to the main page and enter in the station number.")
                        .show();
                break;

            case R.id.activity:
                Intent intent = new Intent(this, Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);

        stationNo = findViewById(R.id.stationEditText);
        searchBtn = findViewById(R.id.searchButton);
        // String StNo = stationNo.getText().toString();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolBar, R.string.open, R.string.close );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        drawer.closeDrawer(GravityCompat.START);
        NavigationView navigationView = findViewById(R.id.popout_menu);
        navigationView.setNavigationItemSelectedListener((item)-> {

            onOptionsItemSelected(item);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        });

        // the recyclerview
        busRouteList = findViewById(R.id.busRoutesList);
        busRouteList.setAdapter(adt);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        busRouteList.setLayoutManager(layoutManager);

        // Shared Preferences
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String savedStationNo = prefs.getString("StationNo", "");
        stationNo.setText(savedStationNo);

        // setting the JSON when the search button is clicked
        searchBtn.setOnClickListener( (click) -> {

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Fetching bus rotes")
                    .setMessage("retrieving all bus routes from " + stationNo.getText().toString() + " station to find the perfect route..")
                    .setView( new ProgressBar(this))
                    .show();

            //making visible
            TextView avaiableBus = findViewById(R.id.availableBuses);
            avaiableBus.setVisibility(View.VISIBLE);

            // Shared Preference
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("StationNo", stationNo.getText().toString());
            editor.apply();



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


                    //declaring JSON objects, arrays
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

                    //setting the text in the runOnUiThread
                    runOnUiThread( () -> {
                        TextView busSt = findViewById(R.id.busStationName);
                        busSt.setText(stopDescription);
                        busSt.setVisibility(View.VISIBLE);

                        busSt = findViewById(R.id.busStationNo);
                        busSt.setText(stopNo);
                        busSt.setVisibility(View.VISIBLE);

                        dialog.hide();
                    });
                }
                catch (IOException | JSONException ioe) {
                    Log.e("Connection error",ioe.getMessage());
                }
            });

            //Toast Message
            //Toast.makeText(getApplicationContext(),"Searching for available Buses in " + stationNo.getText().toString(), Toast.LENGTH_LONG).show();
        });
    }

    /** for rows in the recyclerview */
    private class RowsViews extends RecyclerView.ViewHolder {

        TextView busNum;
        TextView busName;
        String busStationNumber = stationNo.getText().toString();
        int position = 0;


        public RowsViews(View itemView) {
            super(itemView);

            ImageButton deleteBtn = null;
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            // Alert Dialog box and
            deleteBtn.setOnClickListener( click -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(OCTranspoActivity.this);
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



                });

            Button goTo = itemView.findViewById(R.id.goToBtn);
            goTo.setOnClickListener( (clk) -> {
                Intent intent = new Intent(itemView.getContext(), BusRouteDetails.class);
                intent.putExtra("BusNum" , busNum.getText().toString());
                intent.putExtra("BusName", busName.getText().toString());
               intent.putExtra("BusStationNumber", busStationNumber);
                startActivity(intent);
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