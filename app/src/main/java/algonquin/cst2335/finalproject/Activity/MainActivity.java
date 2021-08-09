package algonquin.cst2335.finalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.finalproject.Adapter.ChargingPointAdapter;
import algonquin.cst2335.finalproject.Common.AdapterInterface;
import algonquin.cst2335.finalproject.Common.Api;
import algonquin.cst2335.finalproject.Common.ApiUtils;
import algonquin.cst2335.finalproject.Common.SharedManager;
import algonquin.cst2335.finalproject.Model.AddressInfo;
import algonquin.cst2335.finalproject.Model.Example;
import algonquin.cst2335.finalproject.R;
import algonquin.cst2335.finalproject.Utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterInterface {
    /**declare variables
     *
     */
    private static final String TAG = "MainActivity";
    RecyclerView mRecyclerView;
    ChargingPointAdapter chargingPointAdapter;
    List<AddressInfo> getchargingPointInfo;
    List<Example> example;
    SearchView searchView;
    SharedManager sharedPrefManager;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            /**action with ID action_refresh was selected
             *
             */
            case R.id.savedDataId:
                Intent intent = new Intent(MainActivity.this, AllSavedDataActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/**initialize variable
 *
 */
        mRecyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchView);

        getchargingPointInfo = new ArrayList<>();//initialize list
        chargingPointAdapter = new ChargingPointAdapter(getchargingPointInfo, getApplicationContext(), this);//adding list into adapter
        /**adding adapter into recyclerview*/
        mRecyclerView.setAdapter(chargingPointAdapter);


        sharedPrefManager = new SharedManager(getApplicationContext());
        /** Call method for call Api
         *
         */
        getchargingPointInfoCall();

        /**Search method for searching the data
         *
         */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //                chargingPointAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }

        });


    }

    /** method for filltering the data of list
     *
     * @param text
     */
    private void filter(String text) {
        /** creating a new array list to filter our data.
         *
         */
        ArrayList<AddressInfo> filteredlist = new ArrayList<>();
        /** running a for loop to compare elements.
         *
         */
        for (AddressInfo item : getchargingPointInfo) {
            /** checking if the entered string matched with any item of our recycler view.
             *
             */
            if (item.getAddressLine2().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            /** if no item is added in filtered list we are
             *
             */
            /** displaying a toast message as no data found.
             *
             */
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            /** at last we are passing that filtered
             *
             */
            // list to our adapter class.
            chargingPointAdapter.filterList(filteredlist);
        }
    }

    /**method of Api
     *
     */
    private void getchargingPointInfoCall() {

        Api iRestInterfaces = ApiUtils.getAPIService();
        Call<List<Example>> call = iRestInterfaces.getChargingPoint(Constant.OpenChargeAPi_KEY);

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                example = response.body();


                for (int i = 0; i < example.size(); i++) {
                    AddressInfo addressInfo = new AddressInfo();
                    addressInfo.setTitle(example.get(i).getAddressInfo().getTitle());
                    addressInfo.setTown(example.get(i).getAddressInfo().getTown());
                    addressInfo.setPostcode(example.get(i).getAddressInfo().getPostcode());
                    addressInfo.setAddressLine2(example.get(i).getAddressInfo().getAddressLine1());
                    addressInfo.setLatitude(example.get(i).getAddressInfo().getLatitude());
                    addressInfo.setLongitude(example.get(i).getAddressInfo().getLongitude());
                    addressInfo.setContactTelephone1(example.get(i).getAddressInfo().getContactTelephone1());
                    addressInfo.setContactTelephone2(example.get(i).getAddressInfo().getContactTelephone2());
                    addressInfo.setContactEmail(example.get(i).getAddressInfo().getContactEmail());
                    getchargingPointInfo.add(addressInfo);//adding data into list from api

                }
                if (example.size() > 0) {
                    chargingPointAdapter.notifyDataSetChanged(); //notify the adapter class
                } else {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


    /**method for got to details page on a click of items
     *
     * @param addressInfo
     */
    @Override
    public void onItemClicked(AddressInfo addressInfo) {

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        //store data in intent to show on details page
        sharedPrefManager.putString("Title", addressInfo.getTitle());
        sharedPrefManager.putString("Latitude", String.valueOf(addressInfo.getLatitude()));
        sharedPrefManager.putString("Longitude", String.valueOf(addressInfo.getLongitude()));
        sharedPrefManager.putString("Address", addressInfo.getAddressLine1());
        sharedPrefManager.putString("Tel", addressInfo.getContactTelephone1());
        sharedPrefManager.putString("Telsecond", addressInfo.getContactTelephone2());
        sharedPrefManager.putString("Email", addressInfo.getContactEmail());

        Log.d("TAG", "onItemClicked: " + addressInfo.getLatitude());
        startActivity(intent);


    }
}