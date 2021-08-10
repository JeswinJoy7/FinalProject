package algonquin.cst2335.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A class that will help dealing with database
 */
public class bz_MyOpenHelper extends SQLiteOpenHelper {

    /**
     * database name
     */
    public static final String name = "TheDatabase";

    /**
     * version of the database
     */
    public static final int version = 1;

    /**
     * the text from the news
     */
    public static final String col_message = "Message";

    /**
     * the time news were published here
     */
    public static final String col_time_sent = "TimeSent";

    /**
     * an image related to the news
     */
    public static final String col_url = "URL";


    public bz_MyOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table BZ_News (_id INTEGER PRIMARY KEY AUTOINCREMENT, Message TEXT, TimeSent TEXT, URL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( "drop table if exists BZ_News");
        onCreate(db);

    }
}
