package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

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



        AlertDialog.Builder builder = new AlertDialog.Builder(bz_MainActivity.this);
        builder.setMessage("Please rate the app");
        builder.setTitle("Final Project");

        builder.setNegativeButton("No", (dialog, cl) -> {

        });

        builder.setPositiveButton("Sure", (dialog, cl) -> {

            startActivity(new Intent(bz_MainActivity.this, bz_RateActivity.class));
        });

        builder.create().show();




    }


    public void userClickedMessage(bz_NewsListFragment.PieceOfNews chatMessage, int position) {

        bz_NewsDetailsFragment mdFragment = new bz_NewsDetailsFragment(chatMessage, position);

        if(bz_isTablet){

            getSupportFragmentManager().beginTransaction().replace(R.id.detailsRoom, mdFragment).commit();

        }else{ //phone

            getSupportFragmentManager().beginTransaction().add(R.id.fragmentRoom, mdFragment).commit();

        }

    }


    /*

    Button alert;
    Button toast;
    Button snackBar;
    Button clear;
    Button save;
    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alert = findViewById(R.id.buttonAlert);
        toast = findViewById(R.id.buttonToast);
        snackBar = findViewById(R.id.buttonSnackBar);
        clear = findViewById(R.id.buttonClear );
        txt = findViewById(R.id.editTet);
        save = findViewById(R.id.buttonSave);

        alert.setOnClickListener( e -> {

            AlertDialog.Builder builder = new AlertDialog.Builder( this);
            builder.setMessage("Do stuff?");
            builder.setTitle("Alert: ");

            builder.setNegativeButton("No", (dialog, cl) -> {
            });

            builder.setPositiveButton("Yes", (dialog, cl) -> {
            });

            builder.create().show();

        });

        toast.setOnClickListener( e -> {

            Toast.makeText(this,
                    "This a toast message",
                    Toast.LENGTH_LONG)
                    .show();

        });

        snackBar.setOnClickListener( e -> {

            Snackbar.make( txt , "You clicked snack bar button", Snackbar.LENGTH_LONG)
                    .setAction("OK", clk -> {

                    })
                    .show();

        });

        clear.setOnClickListener( e -> {

            txt.setText("");

        });

        save.setOnClickListener( e -> {

            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor  editor = prefs.edit();
            editor.putString("text", txt.getText().toString());
            editor.apply();

        });

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String text = preferences.getString("text", "");
        txt.setText(text);

    }*/
}