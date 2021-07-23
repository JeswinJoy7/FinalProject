package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

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

    }
}