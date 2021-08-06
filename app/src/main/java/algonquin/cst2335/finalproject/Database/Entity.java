package algonquin.cst2335.finalproject.Database;

import android.provider.BaseColumns;

public final class Entity {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Entity() {
    }

    /* Inner class that defines the table contents */
    public static class FeedChargingPoint implements BaseColumns {
        public static final String TABLE_NAME = "ChargingPoint";
        public static final String COLUMN_NAME_TITLE = "Location_Title";
        public static final String COLUMN_NAME_LATITUDE = "Latitude";
        public static final String COLUMN_NAME_LONGITUDE = "Longitude";
        public static final String COLUMN_NAME_TELEPHONE = "Telephone ";
        public static final String SHAREDPREFMANAGER = "sharedpref ";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedChargingPoint.TABLE_NAME + " (" +
                        FeedChargingPoint._ID + " INTEGER PRIMARY KEY," +
                        FeedChargingPoint.COLUMN_NAME_TITLE + " TEXT," +
                        FeedChargingPoint.COLUMN_NAME_LATITUDE + " TEXT," +
                        FeedChargingPoint.COLUMN_NAME_LONGITUDE + " TEXT," +
                        FeedChargingPoint.COLUMN_NAME_TELEPHONE + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedChargingPoint.TABLE_NAME;


    }

}

