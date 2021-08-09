package algonquin.cst2335.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.collection.CircularArray;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A cragment that will display news
 */
public class bz_NewsDetailsFragment extends Fragment {

    /**
     * A stored piece of news
     */
    bz_NewsListFragment.PieceOfNews bz_chosenPiece;

    /**
     * an id for using the piece of news
     */
    int bz_chosenPosition;

    /**
     * A database object to be able to access it
     */
    SQLiteDatabase bz_db;

    /**
     * A button to get news to saved
     */
    Button bz_save;

    ImageView bz_pic;


    /**
     * a method that creates the fragment for displaying news in a list
     */
    public bz_NewsDetailsFragment(bz_NewsListFragment.PieceOfNews chosenMessage, int chosenPosition){
        this.bz_chosenPiece = chosenMessage;
        this.bz_chosenPosition = chosenPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View detailsView = inflater.inflate(R.layout.bz_details_layout, container, false);

        bz_pic = detailsView.findViewById(R.id.imageDetails);

        setImage(bz_chosenPiece.imageURL);

        bz_save = detailsView.findViewById(R.id.buttonSaveNews);

        TextView messageView = detailsView.findViewById(R.id.messageView);
        TextView idView = detailsView.findViewById(R.id.databaseIdView);
        TextView timeView = detailsView.findViewById(R.id.timeView);

        messageView.setText("Message is: " + bz_chosenPiece.getMessage());
        idView.setText("Database id is:" + bz_chosenPiece.getId());
        timeView.setText(bz_chosenPiece.getTimeSent());

        Button closeButton = detailsView.findViewById(R.id.closeButton);

        closeButton.setOnClickListener(e ->{

            getParentFragmentManager().beginTransaction().remove(this).commit();

        });

        bz_save.setOnClickListener(e ->{

            bz_MyOpenHelper opener = new bz_MyOpenHelper(getContext());
            bz_db = opener.getWritableDatabase();

            ContentValues newRow = new ContentValues();
            newRow.put(bz_MyOpenHelper.col_message, bz_chosenPiece.getMessage());
            newRow.put(bz_MyOpenHelper.col_time_sent, bz_chosenPiece.getTimeSent());
            newRow.put(bz_MyOpenHelper.col_url, bz_chosenPiece.getImageURL());
            long newId = bz_db.insert(bz_MyOpenHelper.TABLE_NAME, bz_MyOpenHelper.col_message, newRow);
            bz_chosenPiece.setId(newId);

        });



        return detailsView;
    }

    /**
     * a method that loads image from internet and loads it into the layout
     * @param icon
     */
    private void setImage(String icon) {

        Picasso.get().load(icon)
                .resize(200, 150)
                .centerCrop()
                .into(bz_pic);
    }
}
