package learnacad.learnacad.com.Adapters;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pulkit-mac on 09/03/18.
 */

public class MyPreferenceManager {

    Context context;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    private static final String PREF_NAME = "com.example.App";
    public static final String USERNAME = "id";
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String QUIZID = "quizid";
    public static final String USERIMAGE = "userimage";
    public static final String QUIZHOUR = "quizhour";
    public static final String QUIZMIN = "quizmin";
    public static final String USERID = "userid";
    public static final String TIMESET = "timeset";
    public static final String FIRSTTIME = "timeset";





    public MyPreferenceManager(Context context) {

        this.context = context;

        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.apply();

    }

    public void putString(String key, String value) {

        editor.putString(key, value);

        editor.apply();

    }

    public String getString(String key) {

        return sharedPreferences.getString(key, null);

    }


    //Method to clear the login data of the application.
    public void clearLoginData() {

        editor.remove(USERNAME);
        editor.remove(KEY_ACCESS_TOKEN);
        editor.apply();

    }


}
