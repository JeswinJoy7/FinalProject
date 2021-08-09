package com.algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class sv_MovieDetailsActivity extends AppCompatActivity {
    Intent intent;
    sv_MovieDatabse dbHelper;
    SQLiteDatabase db;
    ImageView ivMovePoster;
    TextView tvmovienamwe, Released, Runtime, Genre, Director, Writer, Actors, Plot, Language, Country, Awards, BoxOffice, Production, Website;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sv_activity_movie_details);

        ivMovePoster = findViewById(R.id.moviePoster);
        Released = findViewById(R.id.Relea);
        Runtime = findViewById(R.id.Runtime);
        Genre = findViewById(R.id.Genre);
        Director = findViewById(R.id.Director);
        Writer = findViewById(R.id.Writer);
        Actors = findViewById(R.id.Actors);
        Plot = findViewById(R.id.Plot);
        Language = findViewById(R.id.Language);
        Country = findViewById(R.id.Country);
        Awards = findViewById(R.id.Awards);
        BoxOffice = findViewById(R.id.BoxOffice);
        Production = findViewById(R.id.Production);
        Website = findViewById(R.id.Website);
        tvmovienamwe = findViewById(R.id.tvmovienamwe);

        intent = getIntent();
        dbHelper = new sv_MovieDatabse(getApplicationContext());

        Toast.makeText(this, "" + intent.getStringExtra("MovieDetails"), Toast.LENGTH_SHORT).show();

        getAllMovieeDetails(intent.getStringExtra("MovieDetails"));

    }

    private void getAllMovieeDetails(String movieDetails) {


        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(sv_Entity.FeedMovies.TABLE_NAME, null, "_id= " + movieDetails + "", null, null, null, null);

        while (cursor.moveToNext()) {

            tvmovienamwe.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_TITLE)));
            Released.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_RELEASED)));
            Runtime.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_RUNTIME)));
            Genre.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_GENRE)));
            Director.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_DIRECTOR)));
            Writer.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_WRITER)));
            Actors.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_ACTORS)));
            Plot.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_PLOT)));
            Language.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_LANGUAGE)));
            Country.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_COUNTRY)));
            Awards.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_AWARDS)));
            BoxOffice.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_BOXOFFICE)));
            Production.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_PRODUCTION)));
            Website.setText(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_WEBSITE)));

            Glide.with(sv_MovieDetailsActivity.this).load(cursor.getString(cursor.getColumnIndexOrThrow(sv_Entity.FeedMovies.COLUMN_NAME_POSTERS))).error(R.drawable.ic_launcher_background).into(ivMovePoster);


        }

    }
}