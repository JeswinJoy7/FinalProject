package algonquin.cst2335.finalproject.Common;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.List;

public class SharedManager {

    /** this class is for storing value in session
     *
     */

    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private final SharedPreferences sharedprefs;
    private SharedPreferences.Editor editor;
    private static SharedManager appSharedprefs;


    @SuppressLint("CommitPrefEdits")
    public SharedManager(@NonNull Context context) {
        // TODO Auto-generated constructor stub
        this.sharedprefs = context.getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE);
        this.editor = sharedprefs.edit();
    }


    public static SharedManager getInstance(@NonNull Context context) {
        if (appSharedprefs == null)
            appSharedprefs = new SharedManager(context);
        return appSharedprefs;
    }


    public void setLoggedIn(boolean status) {
        this.editor = sharedprefs.edit();
        editor.putBoolean("isLoggedIn", status);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedprefs.getBoolean("isLoggedIn", false);
    }

    public void setToken(String status) {
        this.editor = sharedprefs.edit();
        editor.putString("token", status);
        editor.apply();
    }

    public String getToken() {

        return sharedprefs.getString("token", "");
    }

    public void setFirstTimeLaunch(boolean status) {
        this.editor = sharedprefs.edit();
        editor.putBoolean("firstTimeLaunch", status);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {

        return sharedprefs.getBoolean("firstTimeLaunch", true);

    }

    public void putString(String key, String value) {
        this.editor = sharedprefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedprefs.getString(key, "");
    }


    public <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    public void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getList(String key) {
        return sharedprefs.getString(key, "");
    }


}


