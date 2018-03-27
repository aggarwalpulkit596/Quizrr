package quizrr.quizrr.quizrr;

import android.app.Application;
import android.util.Log;

import com.github.nkzawa.socketio.client.IO;

import java.net.URISyntaxException;

import quizrr.quizrr.quizrr.Adapters.ConnectivityReceiver;
import quizrr.quizrr.quizrr.Adapters.MyPreferenceManager;

/**
 * Created by pulkit-mac on 09/03/18.
 */

public class QuizApp extends Application {
    public static QuizApp myApp;
    public static MyPreferenceManager myPreferenceManager;

    @Override
    public void onCreate() {
        super.onCreate();

        myApp = this;

    }

    private com.github.nkzawa.socketio.client.Socket socket;

    IO.Options options = new IO.Options();

    public com.github.nkzawa.socketio.client.Socket getSocket(String string) {

        if (socket == null) {


            {
                try {

                    options.query = "token=" + string;

                    socket = IO.socket("http://ec2-13-126-208-145.ap-south-1.compute.amazonaws.com:3000/", options);

                    socket.connect();

                    return socket;
                } catch (URISyntaxException e) {

                    Log.d("Errcon", e.getMessage());
                }
            }

        }

        return socket;
    }


    /*For creating the context of the Whole app.*/
    public static synchronized QuizApp getInstance() {
        return myApp;
    }

    /*This is for getting the instance of the MyPreferenceManager class using the context of MyApp App.*/
    public static MyPreferenceManager getPreferenceManager() {
        if (myPreferenceManager == null) {
            myPreferenceManager = new MyPreferenceManager(getInstance());
        }

        return myPreferenceManager;
    }


    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }


}
