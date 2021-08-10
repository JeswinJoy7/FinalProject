package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.Toast;


import com.algonquin.cst2335.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class sv_AllSavedMovies extends AppCompatActivity implements sv_AdapterInterface {

    RecyclerView recyclerView;
    List<sv_ModelMovieInformation> getAllSavedMovies;

    sv_SavedMoviesAdapter savedMoviesAdapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sv_activity_all_saved_movies);

        //getSupportActionBar().hide();                               <--------------------------Bohdan commented out
        recyclerView = findViewById(R.id.saveRecyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        sv_MovieDatabse dbHelper = new sv_MovieDatabse(getApplicationContext());

        getAllSavedMovies = new ArrayList<>();
        savedMoviesAdapter = new sv_SavedMoviesAdapter(this, getAllSavedMovies, this);
        recyclerView.setAdapter(savedMoviesAdapter);


        getAllSavedMovies(dbHelper);


    }

    private void getAllSavedMovies(sv_MovieDatabse dbHelper) {


        db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                sv_Entity.FeedMovies.COLUMN_NAME_TITLE,
                sv_Entity.FeedMovies.COLUMN_NAME_RELEASED,
                sv_Entity.FeedMovies.COLUMN_NAME_RUNTIME,
                sv_Entity.FeedMovies.COLUMN_NAME_GENRE,
                sv_Entity.FeedMovies.COLUMN_NAME_DIRECTOR,
                sv_Entity.FeedMovies.COLUMN_NAME_WRITER,
                sv_Entity.FeedMovies.COLUMN_NAME_ACTORS,
                sv_Entity.FeedMovies.COLUMN_NAME_PLOT,
                sv_Entity.FeedMovies.COLUMN_NAME_LANGUAGE,
                sv_Entity.FeedMovies.COLUMN_NAME_COUNTRY,
                sv_Entity.FeedMovies.COLUMN_NAME_AWARDS,
                sv_Entity.FeedMovies.COLUMN_NAME_BOXOFFICE,
                sv_Entity.FeedMovies.COLUMN_NAME_PRODUCTION,
                sv_Entity.FeedMovies.COLUMN_NAME_WEBSITE,
                sv_Entity.FeedMovies.COLUMN_NAME_POSTERS};


        Cursor cursor = db.query(sv_Entity.FeedMovies.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            sv_ModelMovieInformation modelMovieInformation = new sv_ModelMovieInformation();
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies._ID));
            modelMovieInformation.setId(cursor.getInt(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies._ID)));
            modelMovieInformation.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_TITLE)));
            modelMovieInformation.setReleased(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_RELEASED)));
            modelMovieInformation.setRuntime(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_RUNTIME)));
            modelMovieInformation.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_GENRE)));
            modelMovieInformation.setDirector(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_DIRECTOR)));
            modelMovieInformation.setWriter(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_WRITER)));
            modelMovieInformation.setActors(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_ACTORS)));
            modelMovieInformation.setPlot(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_PLOT)));
            modelMovieInformation.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_LANGUAGE)));
            modelMovieInformation.setCountry(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_COUNTRY)));
            modelMovieInformation.setAwards(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_AWARDS)));
            modelMovieInformation.setBoxOffice(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_BOXOFFICE)));
            modelMovieInformation.setProduction(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_PRODUCTION)));
            modelMovieInformation.setWebsite(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_WEBSITE)));
            modelMovieInformation.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_POSTERS)));
            getAllSavedMovies.add(modelMovieInformation);

        }

        if (getAllSavedMovies.size() > 0) {
            savedMoviesAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(sv_AllSavedMovies.this, "no data found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();

    }


    @Override
    public void onItemClicked(sv_ModelMovieInformation modelMovieInformation) {

        String selection = sv_Entity.FeedMovies._ID;
// Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(modelMovieInformation.getId())};
// Issue SQL statement.
        int deletedRows = db.delete(sv_Entity.FeedMovies.TABLE_NAME, "_id= " + modelMovieInformation.getId() + "", null);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMvieInformation(sv_ModelMovieInformation modelMovieInformation) {
        Intent intent = new Intent(sv_AllSavedMovies.this, sv_MovieDetailsActivity.class);
        intent.putExtra("MovieDetails", String.valueOf(modelMovieInformation.getId()));
        startActivity(intent);

    }
}
