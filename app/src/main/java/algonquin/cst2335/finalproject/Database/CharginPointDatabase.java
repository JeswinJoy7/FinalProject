package algonquin.cst2335.finalproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CharginPointDatabase extends SQLiteOpenHelper {


    /**local database class to create table and database
     *
     */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ChargingPointDb";


    public CharginPointDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Entity.FeedChargingPoint.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /** This database is only a cache for online data, so its upgrade policy is
         *
         */
        /** to simply to discard the data and start over
         *
         */
        db.execSQL(Entity.FeedChargingPoint.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
