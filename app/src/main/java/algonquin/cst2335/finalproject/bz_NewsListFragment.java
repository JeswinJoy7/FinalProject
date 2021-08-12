package algonquin.cst2335.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.algonquin.cst2335.finalproject.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 */
public class bz_NewsListFragment extends Fragment {


    /**
     * The layout that contains button to move to saved
     */
    View bz_listedLayout;


    /**
     *
     */
    RecyclerView bz_recyclerList;

    /**
     * A list of all the news
     */
    ArrayList<PieceOfNews> bz_newsArray = new ArrayList<>();

    /**
     * An object of adapter to deal with the layout
     */
    MyChatAdapter bz_adt;

    /**
     * A database object to be able to access it
     */
    SQLiteDatabase bz_db;


    /**
     * a button that sends user to a page with saved items
     */
    Button bz_goTo;

    /**
     * a button that sends user to page with news
     */
    Button bz_toNews;

    /**
     * A variable that equals adapter's position to call method onBindViewHolder
     */
    int bz_pos;

    /**
     * A variable that equals adapter's holder to call method onBindViewHolder
     */
    MyRowViews bz_holder;

    bz_MainActivity main = (bz_MainActivity)getActivity();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        bz_listedLayout = inflater.inflate(R.layout.bz_news, container, false);



        bz_newsArray.clear();
        getFromWeb();



//            bz_goTo = bz_listedLayout.findViewById(R.id.toSaved);
//
//            bz_goTo.setOnClickListener(e ->{
//               clearRecycler();
//               bz_newsArray.clear();
//               getFromWeb();
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Switched to news",
//                        Toast.LENGTH_LONG)
//                        .show();
//            });

            bz_toNews = bz_listedLayout.findViewById(R.id.toSaved);

            bz_toNews.setOnClickListener(e ->{
                clearRecycler();
                bz_newsArray.clear();
                populate();
                Toast.makeText(getActivity().getApplicationContext(),
                        "Switched to saved",
                        Toast.LENGTH_LONG)
                        .show();
            });



        bz_recyclerList = bz_listedLayout.findViewById(R.id.bz_recycler);
        bz_recyclerList.setAdapter(bz_adt = new MyChatAdapter());

        bz_recyclerList = bz_listedLayout.findViewById(R.id.bz_recycler);
        bz_recyclerList.setAdapter(bz_adt = new MyChatAdapter());
        bz_recyclerList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return bz_listedLayout;

    }

    private void clearRecycler(){

        for(int i = bz_adt.getItemCount(); i>0; i--) {
            bz_adt.notifyItemRemoved(i);
        }

    }


    /**
     * A method that is called to display more details about the piece of news
     */
    private class MyRowViews extends RecyclerView.ViewHolder{


        TextView timeText;
        TextView messageText;
        int position = -1;

        public MyRowViews(View itemView) {
            super(itemView);

            itemView.setOnClickListener( e -> {

                bz_MainActivity parentActivity = (bz_MainActivity)getContext();
                parentActivity.userClickedMessage(bz_newsArray.get(position), position);


            });

            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
        public void setPosition(int p){ position = p; }



    }


    /**
     * a class that takes care of the layout of news by assigning them a position when they are created
     */
    private class MyChatAdapter extends RecyclerView.Adapter<MyRowViews> {


        @Override
        public MyRowViews onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            int layoutID = R.layout.bz_sent_message;

            View loadedRow = inflater.inflate(layoutID, parent, false);

            return new MyRowViews(loadedRow);

        }

        @Override
        public void onBindViewHolder(MyRowViews holder, int position) {

            holder.messageText.setText(bz_newsArray.get(position).getMessage());
            holder.timeText.setText(bz_newsArray.get(position).getTimeSent());
            holder.setPosition(position);
            bz_pos = position;
            bz_holder = holder;

        }

        @Override
        public int getItemCount() {
            return bz_newsArray.size();
        }
    }


    /**
     * An class to create objects containing the news
     */
    public class PieceOfNews{

        String imageURL;
        String message;
        String timeSent;
        long id;

        public void setId(long l){ this.id = l; }

        public void setImageURL(String url){ this.imageURL = url; }

        public long getId() {return this.id; }

        public String getImageURL() {return this.imageURL; }

        public String getMessage() {
            return message;
        }

        public String getTimeSent() {
            return timeSent;
        }

        public PieceOfNews(String message, String timeSent) {
            this.message = message;
            this.timeSent = timeSent;
        }

        public PieceOfNews(String message, String timeSent, long id, String url) {
            this.message = message;
            this.timeSent = timeSent;
            this.imageURL = url;
            setId(id);
        }



    }

    public void populate(){

        bz_MyOpenHelper opener = new bz_MyOpenHelper(getContext());
        bz_db = opener.getWritableDatabase();

        Cursor results = bz_db.rawQuery("SELECT * FROM BZ_News;", null);

        int idCol = results.getColumnIndex("_id");
        int messageCol = results.getColumnIndex(bz_MyOpenHelper.col_message);
        int timeCol = results.getColumnIndex(bz_MyOpenHelper.col_time_sent);
        int urlCol = results.getColumnIndex(bz_MyOpenHelper.col_url);

        int i = 0;
        while(results.moveToNext()) {
            int id = results.getInt(idCol);
            //int id = i++;
            String message = results.getString(messageCol);
            String time = results.getString(timeCol);
            String icon = results.getString(urlCol);

            bz_newsArray.add(new PieceOfNews(message + "", "" + time, id, icon));

            //bz_adt.notifyItemInserted(bz_newsArray.size() - 1);
        }
        bz_adt.notifyDataSetChanged();

    }

    /**
     * A method that gets the data from web to display news
     */
    public void getFromWeb(){

        Executor newThread = Executors.newSingleThreadExecutor();

        newThread.execute(() -> {

                    try {
                        URL url = new URL("http://www.goal.com/en/feeds/news?fmt=rss");
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        String redirect = urlConnection.getHeaderField("Location");
                        if (redirect != null) {
                            urlConnection = (HttpURLConnection) new URL(redirect).openConnection();
                        }

                        Log.w( "MainActivity", "connection " + urlConnection.getResponseMessage() + " " + urlConnection.getResponseCode());
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        factory.setNamespaceAware(false);
                        XmlPullParser xpp = factory.newPullParser();
                        xpp.setInput(in, "UTF-8");
                        Log.w( "MainActivity", "setup" );

                        int i = 0;

                        int eventType = xpp.getEventType();

                        while (eventType != XmlPullParser.END_DOCUMENT) {

                            String description = "description";

                            String icon = "";

                            String time = "12";

                            Log.w("MainActivity", "eventType " + eventType + "  name " + xpp.getName());



                            if ((xpp.getEventType() + "").equals("")) {
                                Log.w( "MainActivity", "null" );
                            } else if (eventType == XmlPullParser.START_DOCUMENT) {

                                Log.w( "MainActivity", "start" );
                            } else if (eventType == XmlPullParser.START_TAG && xpp != null){


                                if(((xpp.getName() + "").equals("item")) ) {

                                    i++;

                                    Log.w("MainActivity", "eventType " + eventType + " <item> ");

                                    eventType = xpp.next();

                                    while(!(xpp.getName() + "").equals("item")){


                                    if ((xpp.getName() + "").equals("description") && eventType == XmlPullParser.START_TAG) {

                                        int token = xpp.nextToken();
                                        while(token!=XmlPullParser.CDSECT){
                                            token = xpp.nextToken();
                                        }
                                        String cdata = xpp.getText();

                                        Log.i("MainActivity", "CDATA" + cdata);
                                        description = "" + cdata;

                                        Log.w("MainActivity", "eventType " + eventType + " <description>  " + xpp.getName());
                                        Log.w("MainActivity", "=== description--->" + description);

                                    }
                                    if ((xpp.getName() + "").equals("media:thumbnail") && eventType == XmlPullParser.START_TAG) {

                                        icon = xpp.getAttributeValue(null, "url");
                                        Log.w("MainActivity", "eventType " + eventType + " <thumbnail> " + xpp.getName());
                                        Log.w("MainActivity", "=== icon --->" + icon);

                                    } else if ((xpp.getName() + "").equals("pubDate") && eventType == XmlPullParser.START_TAG) {

                                        time = xpp.nextText();
                                        Log.w("MainActivity", "eventType " + eventType + " <time> " + xpp.getName());
                                        Log.w("MainActivity", "=== time --->" + time);

                                    }
                                        eventType = xpp.next();

                                }
                                    bz_newsArray.add(new PieceOfNews(description + "", "" + time, i, icon));

                                    Log.w("MainActivity", "eventType " + eventType + " </item>");
                                }

                                } else if (eventType == XmlPullParser.TEXT){

                                Log.w("MainActivity", "eventType " + eventType + " name " + xpp.getName());

                            }else if (eventType == XmlPullParser.END_TAG){

                            } else {
                                Log.w("MainActivity", "eventType " + eventType + " name " + xpp.getName());
                            }

                            if(i == 30){
                                break;
                            }

                            eventType = xpp.next();

                        }



                        /*}catch (IOException | XmlPullParserException eio) {
                        eio.printStackTrace();
                    }*/
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    });


        for(int i = 0; i< bz_newsArray.size(); i++){
            bz_adt.notifyItemInserted(bz_newsArray.size() - 1);
        }


        }




}