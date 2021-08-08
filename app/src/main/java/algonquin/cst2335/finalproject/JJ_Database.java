package algonquin.cst2335.finalproject;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.material.tabs.TabLayout;

public class JJ_Database extends SQLiteOpenHelper {

    public static  final String name = "TheDatabase";
    public static final int version = 1;
    public static final String TABLE_NAME = "BusRoutes";
    public static final String col_busName = "BusName";
    public static final String col_busNumber = "BusNumber";
    public JJ_Database(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + " ( _Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + col_busNumber + " TEXT, "
                    + col_busName + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
