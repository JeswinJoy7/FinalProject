package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquin.cst2335.finalproject.R;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sv_MainActivity extends AppCompatActivity {

    EditText editTextTextPersonName;
    private static final String TAG = "MainActivity";
    Button search, save;
    ImageView ivMovePoster;

    String apikey = "6c9862c2";
    TextView tvmovienamwe, Released, Runtime, Genre, Director, Writer, Actors, Plot, Language, Country, Awards, BoxOffice, Production, Website;

    ScrollView scrollView;
    sv_ModelMovieInformation modelMovieInformation;
    TextView viewAllTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sv_activity_main);

        sv_MovieDatabse dbHelper = new sv_MovieDatabse(getApplicationContext());
        //getSupportActionBar().hide();                      <-------------------Bohdan commented out
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        search = findViewById(R.id.button);
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
        scrollView = findViewById(R.id.ScrollView);
        save = findViewById(R.id.save);
        viewAllTextview = findViewById(R.id.ViewAllSaved);


        viewAllTextview.setOnClickListener(view -> {
            Intent intent = new Intent(sv_MainActivity.this, sv_AllSavedMovies.class);
            startActivity(intent);
        });


        save.setOnClickListener(v -> {

            /**Toast Message*/
            Toast.makeText(getApplicationContext(),"Saving movie data", Toast.LENGTH_LONG).show();

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_TITLE, modelMovieInformation.getTitle());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_RELEASED, modelMovieInformation.getReleased());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_RUNTIME, modelMovieInformation.getRuntime());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_GENRE, modelMovieInformation.getGenre());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_DIRECTOR, modelMovieInformation.getDirector());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_WRITER, modelMovieInformation.getWriter());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_ACTORS, modelMovieInformation.getActors());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_PLOT, modelMovieInformation.getPlot());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_LANGUAGE, modelMovieInformation.getLanguage());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_COUNTRY, modelMovieInformation.getCountry());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_AWARDS, modelMovieInformation.getAwards());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_BOXOFFICE, modelMovieInformation.getBoxOffice());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_PRODUCTION, modelMovieInformation.getProduction());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_WEBSITE, modelMovieInformation.getWebsite());
            values.put(sv_Entity.FeedMovies.COLUMN_NAME_POSTERS, modelMovieInformation.getPoster());

            long newrow = db.insert(sv_Entity.FeedMovies.TABLE_NAME, null, values);

            Log.d(TAG, "onClick: " + newrow);
        });


        search.setOnClickListener(v -> {
            moviewsearchRetrofit();
        });
    }

    private void moviewsearchRetrofit() {

        String name = editTextTextPersonName.getText().toString();

        if (name.isEmpty()) {
            editTextTextPersonName.setError("Enter Movie Name");
            return;
        }

        sv_Api iRestInterfaces = sv_ApiUtils.getAPIService();
        Call<sv_ModelMovieInformation> call = iRestInterfaces.getmoviewInformation(name, "Full", "6c9862c2");

        call.enqueue(new Callback<sv_ModelMovieInformation>() {
            @Override
            public void onResponse(Call<sv_ModelMovieInformation> call, Response<sv_ModelMovieInformation> response) {
                modelMovieInformation = response.body();
                if (response.isSuccessful()) {
                    tvmovienamwe.setText(modelMovieInformation.getTitle());
                    Released.setText(modelMovieInformation.getReleased());
                    Runtime.setText(modelMovieInformation.getRuntime());
                    Genre.setText(modelMovieInformation.getGenre());
                    Director.setText(modelMovieInformation.getDirector());
                    Writer.setText(modelMovieInformation.getWriter());
                    Actors.setText(modelMovieInformation.getActors());
                    Plot.setText(modelMovieInformation.getPlot());
                    Language.setText(modelMovieInformation.getLanguage());
                    Country.setText(modelMovieInformation.getCountry());
                    Awards.setText(modelMovieInformation.getAwards());
                    BoxOffice.setText(modelMovieInformation.getBoxOffice());
                    Production.setText(modelMovieInformation.getProduction());
                    Website.setText(modelMovieInformation.getWebsite());
                    Glide.with(sv_MainActivity.this).load(modelMovieInformation.getPoster()).error(R.drawable.ic_launcher_background).into(ivMovePoster);
                } else {
                    Toast.makeText(sv_MainActivity.this, "Movie Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<sv_ModelMovieInformation> call, Throwable t) {
                Toast.makeText(sv_MainActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}