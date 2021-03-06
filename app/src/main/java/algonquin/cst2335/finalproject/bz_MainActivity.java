package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;

import com.algonquin.cst2335.finalproject.R;

import algonquin.cst2335.finalproject.Activity.MainActivity;

/**
 * A class that serves as football dames api
 */
public class bz_MainActivity extends AppCompatActivity {

    /**
     * Checks whether the app is running on a tablet
     */
    boolean bz_isTablet = false;

    /**
     * A container for news
     */
    bz_NewsListFragment bz_newsFragment;


    /**
     * A variable that lets the page reload when used chooses not to rate
     */
    boolean ask = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bz_empty_layout);



        bz_isTablet = findViewById(R.id.detailsRoom) != null;
        FragmentManager fMgr = getSupportFragmentManager();
        FragmentTransaction tx = fMgr.beginTransaction();
        bz_newsFragment = new bz_NewsListFragment();
        tx.add(R.id.fragmentRoom, bz_newsFragment);
        tx.commit();

        Intent fromPrevious = getIntent();
        ask = fromPrevious.getBooleanExtra("shouldAsk", true);

        runOnUiThread(() -> {
            Toolbar bz_myToolbar = findViewById(R.id.bz_toolbar);
            setSupportActionBar(bz_myToolbar);
        });


        if(ask) {
            AlertDialog.Builder builder = new AlertDialog.Builder(bz_MainActivity.this);
            builder.setMessage("Please rate the app");
            builder.setTitle("Final Project");

            builder.setNegativeButton("No", (dialog, cl) -> {

                Intent restart = new Intent(bz_MainActivity.this, bz_MainActivity.class);
                restart.putExtra("shouldAsk", false);
                startActivity(restart);

            });

            builder.setPositiveButton("Sure", (dialog, cl) -> {

                startActivity(new Intent(bz_MainActivity.this, bz_RateActivity.class));
            });

            builder.create().show();


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bz_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.bz_name:

                Intent back = new Intent(bz_MainActivity.this, MainActivity.class);
                startActivity(back);

                break;
            case R.id.bz_info:

                AlertDialog.Builder builder = new AlertDialog.Builder(bz_MainActivity.this);
                builder.setMessage("This app loads spot news data\n click any item to see more");
                builder.setTitle("App info");

                builder.create().show();



                break;

        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A method that gets called when a user clicks to see piece of news details
     * it checks on what type of device it is running and displays info in a way based on that
     * @param chatMessage
     * @param position
     */
    public void userClickedMessage(bz_NewsListFragment.PieceOfNews chatMessage, int position) {

        bz_NewsDetailsFragment mdFragment = new bz_NewsDetailsFragment(chatMessage, position);


        if(bz_isTablet){

            getSupportFragmentManager().beginTransaction().replace(R.id.detailsRoom, mdFragment).commit();

        }else{ //phone

            getSupportFragmentManager().beginTransaction().add(R.id.fragmentRoom, mdFragment).commit();

        }

    }



}