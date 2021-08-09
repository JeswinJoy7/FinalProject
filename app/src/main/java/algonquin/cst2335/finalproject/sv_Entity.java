package algonquin.cst2335.finalproject;

import android.provider.BaseColumns;

public final class sv_Entity {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private sv_Entity() {
    }

    /* Inner class that defines the table contents */
    public static class FeedMovies implements BaseColumns {
        public static final String TABLE_NAME = "Movies";
        public static final String COLUMN_NAME_TITLE = "Title";
        public static final String COLUMN_NAME_RELEASED = "Released";
        public static final String COLUMN_NAME_RUNTIME = "Runtime";
        public static final String COLUMN_NAME_GENRE = "Genre";
        public static final String COLUMN_NAME_DIRECTOR = "Director";
        public static final String COLUMN_NAME_WRITER = "Writer";
        public static final String COLUMN_NAME_ACTORS = "Actors";
        public static final String COLUMN_NAME_PLOT = "Plot";
        public static final String COLUMN_NAME_LANGUAGE = "Language";
        public static final String COLUMN_NAME_COUNTRY = "Country";
        public static final String COLUMN_NAME_AWARDS = "Awards";
        public static final String COLUMN_NAME_BOXOFFICE = "BoxOffice";
        public static final String COLUMN_NAME_PRODUCTION = "Production";
        public static final String COLUMN_NAME_WEBSITE = "Website";
        public static final String COLUMN_NAME_POSTERS = "Poster";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedMovies.TABLE_NAME + " (" +
                        FeedMovies._ID + " INTEGER PRIMARY KEY," +
                        FeedMovies.COLUMN_NAME_TITLE + " TEXT," +
                        FeedMovies.COLUMN_NAME_RELEASED + " TEXT," +
                        FeedMovies.COLUMN_NAME_RUNTIME + " TEXT," +
                        FeedMovies.COLUMN_NAME_GENRE + " TEXT," +
                        FeedMovies.COLUMN_NAME_DIRECTOR + " TEXT," +
                        FeedMovies.COLUMN_NAME_WRITER + " TEXT," +
                        FeedMovies.COLUMN_NAME_ACTORS + " TEXT," +
                        FeedMovies.COLUMN_NAME_PLOT + " TEXT," +
                        FeedMovies.COLUMN_NAME_LANGUAGE + " TEXT," +
                        FeedMovies.COLUMN_NAME_COUNTRY + " TEXT," +
                        FeedMovies.COLUMN_NAME_AWARDS + " TEXT," +
                        FeedMovies.COLUMN_NAME_BOXOFFICE + " TEXT," +
                        FeedMovies.COLUMN_NAME_PRODUCTION + " TEXT," +
                        FeedMovies.COLUMN_NAME_WEBSITE + " TEXT," +
                        FeedMovies.COLUMN_NAME_POSTERS + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedMovies.TABLE_NAME;


    }

}

