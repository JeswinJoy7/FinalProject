package algonquin.cst2335.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.algonquin.cst2335.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.finalproject.Adapter.ChargingPointAdapter;
import algonquin.cst2335.finalproject.Common.AdapterInterface;
import algonquin.cst2335.finalproject.Database.CharginPointDatabase;
import algonquin.cst2335.finalproject.Database.Entity;
import algonquin.cst2335.finalproject.Model.AddressInfo;

public class AllSavedDataActivity extends AppCompatActivity implements AdapterInterface {

    RecyclerView recyclerView;
    List<AddressInfo> addressInfoList;

    ChargingPointAdapter chargingPointAdapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_saved_data);


        recyclerView = findViewById(R.id.saveRecyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        CharginPointDatabase dbHelper = new CharginPointDatabase(getApplicationContext());

        addressInfoList = new ArrayList<>();
        chargingPointAdapter = new ChargingPointAdapter(addressInfoList, this, this);
        recyclerView.setAdapter(chargingPointAdapter);

        getAllSavedData(dbHelper);
    }

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

        }

        if (addressInfoList.size() > 0) {
            chargingPointAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(AllSavedDataActivity.this, "no data found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();

    }

    @Override
    public void onItemClicked(AddressInfo addressInfo) {


    }
}