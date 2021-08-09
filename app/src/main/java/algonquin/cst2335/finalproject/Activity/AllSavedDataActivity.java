package algonquin.cst2335.finalproject.Activity;

import algonquin.cst2335.finalproject.Adapter.ChargingSavedDataAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.finalproject.Common.AdapterInterface;
import algonquin.cst2335.finalproject.Database.CharginPointDatabase;
import algonquin.cst2335.finalproject.Database.Entity;
import algonquin.cst2335.finalproject.Model.AddressInfo;
import algonquin.cst2335.finalproject.R;

public class AllSavedDataActivity extends AppCompatActivity implements AdapterInterface {

    /**
     *   declare variable
     */
    RecyclerView recyclerView;
    List<AddressInfo> addressInfoList;
    ChargingSavedDataAdapter charginSavedAdapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_saved_data);

        /**
         * initialize variable*

         */

        recyclerView = findViewById(R.id.saveRecyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        CharginPointDatabase dbHelper = new CharginPointDatabase(getApplicationContext());

        /**initialize list here*/
        addressInfoList = new ArrayList<>();
        /**add list into Adapter*/
        charginSavedAdapter = new ChargingSavedDataAdapter(addressInfoList, this);
        /**adding adapter to our recyclerview*/
        recyclerView.setAdapter(charginSavedAdapter);
        /**call method for getting all the data from database
         *
         */
        getAllSavedData(dbHelper);
    }

    /**method for get All Saved Data
     *
     * @param dbHelper
     */
    private void getAllSavedData(CharginPointDatabase dbHelper) {

        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(Entity.FeedChargingPoint.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            AddressInfo addressInfo = new AddressInfo();
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(Entity.FeedChargingPoint._ID));
            addressInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Entity.FeedChargingPoint._ID)));
            addressInfo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Entity.FeedChargingPoint.COLUMN_NAME_TITLE)));
            addressInfo.setTown(cursor.getString(cursor.getColumnIndexOrThrow(Entity.FeedChargingPoint.COLUMN_NAME_LATITUDE)));
            addressInfoList.add(addressInfo);
            /**adding data into list from database*/


        }

        if (addressInfoList.size() > 0) {
            /**here we notify the data adapter if any changes occurs*/
            charginSavedAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(AllSavedDataActivity.this, "no data found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();

    }


    /**method for detele the item
     *
     * @param addressInfo
     */
    @Override
    public void onItemClicked(AddressInfo addressInfo) {
        int deletedRows = db.delete(Entity.FeedChargingPoint.TABLE_NAME, "_id= " + addressInfo.getId() + "", null);
        if (deletedRows > 0) {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show();
        }
    }
}