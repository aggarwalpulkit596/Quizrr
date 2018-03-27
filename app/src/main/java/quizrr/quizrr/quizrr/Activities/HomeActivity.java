package quizrr.quizrr.quizrr.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.JsonObject;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
import quizrr.quizrr.quizrr.Adapters.AlarmReceiver;
import quizrr.quizrr.quizrr.Adapters.ConnectivityReceiver;
import quizrr.quizrr.quizrr.Adapters.MyPreferenceManager;
import quizrr.quizrr.quizrr.Adapters.NotificationScheduler;
import quizrr.quizrr.quizrr.Game2Activity;
import quizrr.quizrr.quizrr.InfoActivies.AboutActivity;
import quizrr.quizrr.quizrr.InfoActivies.FutureeventsActivity;
import quizrr.quizrr.quizrr.MODELS.Quiz;
import quizrr.quizrr.quizrr.MODELS.userfields;
import quizrr.quizrr.quizrr.QuizApp;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.REST.ApiClients;
import quizrr.quizrr.quizrr.REST.ApiInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    @BindView(R.id.userImage)
    CircleImageView circleImageView;
    @BindView(R.id.timetextview)
    TextView timetextview;
    @BindView(R.id.startingintextview)
    TextView startingintextview;
    @BindView(R.id.notificationtextview)
    TextView notificationtextview;
    @BindView(R.id.usernametextview)
    TextView usernametextview;
    @BindView(R.id.prizesearnedtextview)
    TextView prizesearnedtextview;
    @BindView(R.id.prizeamounttextview)
    ImageView prizeamounttextview;
    @BindView(R.id.liveslefttextview)
    TextView liveslefttextview;
    @BindView(R.id.homeinfobutton)
    ImageButton infobutton;
    @BindView(R.id.homecalendar)
    ImageButton calendarbutton;
    @BindView(R.id.homerootlayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.topictextview)
    TextView topictextview;
    @BindView(R.id.notificationimage)
    ImageView notificationimage;


    boolean doubleBackToExitPressedOnce = false;
    String token;
    ProgressBar progressBar;
    String id;
    QuizApp application;
    Socket mSocket;
    AlertDialog deleteDialog;
    Quiz quiz;
    ConnectivityReceiver connectivityReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        token = QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN);
        if (token == null) {
            startActivity(new Intent(HomeActivity.this, SplashActivity.class));
            finish();
        }
        application = (QuizApp) getApplication();
        QuizApp.getPreferenceManager().putString(MyPreferenceManager.TIMESET, "false");


        //load data
        progressBar = new ProgressBar(HomeActivity.this, null, android.R.attr.progressBarStyleLargeInverse);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(progressBar, params);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setElevation(10);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);//To show ProgressBar
        fetchdata();
        fetchquizdata();
        extras();

    }

    private void extras() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.dialog_info, null);
        deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialog.getWindow().setBackgroundDrawableResource(R.drawable.menubackground);
        deleteDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();

            }
        });

        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
            }
        });
        calendarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FutureeventsActivity.class));
            }
        });

    }

    private void fetchdata() {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<userfields> call = apiService.user(token);
        call.enqueue(new Callback<userfields>() {
            @Override
            public void onResponse(Call<userfields> call, Response<userfields> response) {

                if (response.isSuccessful()) {
                    userfields userfields = response.body();
                    if (userfields.getImageUrl() != null) {
                        QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERIMAGE, userfields.getImageUrl());
                        Picasso.get().load(userfields.getImageUrl()).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.drawable.default_avatar).into(circleImageView);
                    }

                    prizesearnedtextview.setText(userfields.getAmountWon() + "");
                    liveslefttextview.setText(userfields.getLifeLine() + "");
                    usernametextview.setText(userfields.getUserName());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERIMAGE, userfields.getImageUrl());
                    QuizApp.getPreferenceManager().putString(MyPreferenceManager.USERNAME, userfields.getUserName());
                    id = userfields.getId();

                    progressBar.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            }

            @Override
            public void onFailure(Call<userfields> call, Throwable t) {
                Log.d("POST_API", t.getCause() + "");
            }
        });

    }

    private void fetchquizdata() {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<Quiz> call = apiService.quiz();
        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {

                if (response.isSuccessful()) {
                    quiz = response.body();
                    if (quiz != null) {
                        topictextview.setText(quiz.getTopic());
                        topictextview.setTextSize(24);
                        timetextview.setText(formatDate(quiz.getDate() * 1000));
                        timetextview.setTextSize(18);
                        Picasso.get().load(quiz.getPrizUrl()).networkPolicy(NetworkPolicy.NO_CACHE).into(prizeamounttextview);
                        QuizApp.getPreferenceManager().putString(MyPreferenceManager.QUIZID, quiz.getId());
                        if (quiz.getIsLive()) {
                            mSocket = application.getSocket(token);
                            timesocket();
                            playersocket();
                        }

                        if (!QuizApp.getPreferenceManager().getString(MyPreferenceManager.TIMESET).equals("true")) {
                            QuizApp.getPreferenceManager().putString(MyPreferenceManager.TIMESET, "true");
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    fetchquizdata();
                                }
                            }, (quiz.getDate() * 1000 - (System.currentTimeMillis())) - 540000);
                        }

                    } else {
                        topictextview.setText(quiz.getTopic());
                    }

                }
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                Log.d("POST_API", t.getCause() + "");
            }
        });

    }

    public void timesocket() {
        mSocket.on("time_left", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject response = (JSONObject) args[0];
                        Log.d("checkrea", Arrays.toString(args));
                        int timeNextQuestionIn;
                        try {
                            timeNextQuestionIn = response.getInt("timeleft");
                            int min = timeNextQuestionIn / 60;
                            int sec = timeNextQuestionIn % 60;
                            timetextview.setText("Starts in  " + String.format("%02d", min) + ":" + String.format("%02d", sec));

                            if (timeNextQuestionIn <= 180) {
                                Intent i = new Intent(HomeActivity.this, InstructionActivity.class);
                                i.putExtra("quiz", quiz);
                                i.putExtra("_id", id);
                                if (quiz.getIsLate()) {
                                    Intent game = new Intent(HomeActivity.this, Game2Activity.class);
                                    game.putExtra("_id", id);
                                    game.putExtra("quiz", quiz);
                                    startActivity(game);
                                    finish();
                                    mSocket.off("time_left");
                                    mSocket.off("live_people");
                                    mSocket.off("error");
                                } else {
                                    startActivity(i);
                                    mSocket.off("time_left");
                                    mSocket.off("live_people");
                                    mSocket.off("error");
                                }

                            }
                        } catch (
                                JSONException e)

                        {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        mSocket.on("error", new Emitter.Listener()

        {
            @Override
            public void call(final Object... args) {

                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("checkrea", Arrays.toString(args));

                    }
                });
            }
        });
    }

    public void playersocket() {
        mSocket.on("live_people", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject response = (JSONObject) args[0];
                        Log.d("checkrea", Arrays.toString(args));

                        try {
                            notificationimage.setImageResource(R.drawable.users);
                            int count = response.getInt("count");
                            notificationtextview.setText(count + " Players have joined");

                        } catch (JSONException e) {

                        }
                    }
                });
            }
        });
    }

    public void setCalender(View view) {
        if (quiz != null) {
            int hours = Integer.parseInt(QuizApp.getPreferenceManager().getString(MyPreferenceManager.QUIZHOUR));
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra(CalendarContract.Events.TITLE, quiz.getTopic() + " - Quizrr");
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, quiz.getDate() * 1000 - 600000);
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                    quiz.getEndTime() * 1000);
            intent.putExtra(CalendarContract.Events.ALL_DAY, false);// periodicity
            intent.putExtra(CalendarContract.Events.DESCRIPTION, "Play tonight's quiz at " + hours + " PM on Quizrr. Learn something new, win cash prizes and have fun.");
            startActivity(intent);

        }

    }

    public void changeimage(View view) {
        CropImage.activity()
                .setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMinCropWindowSize(500, 500)
                .start(HomeActivity.this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File compressedImageFile = null;
                try {
                    compressedImageFile = new Compressor(this).setQuality(10).compressToFile(new File(resultUri.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                changeprofileimage(compressedImageFile);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void changeprofileimage(final File resultUri) {

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), resultUri);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", resultUri.getAbsolutePath(), requestFile);
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiService.changeprofile(token, body);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Picasso.get().load(resultUri).into(circleImageView);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void share(View view) {
        startActivity(new Intent(HomeActivity.this, ShareActivity.class));
    }

    public void leaderbaord(View view) {
        Intent i = new Intent(HomeActivity.this, PastQuizesActivity.class);
        i.putExtra("quizid", quiz.getId());
        startActivity(i);

    }


    public void prizemodal(View view) {
        deleteDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        QuizApp.getPreferenceManager().putString(MyPreferenceManager.TIMESET, "false");
        fetchquizdata();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);

        /*register connection status listener*/
        QuizApp.getInstance().setConnectivityListener(this);
    }

    private String formatDate(long milliseconds) /* This is your topStory.getTime()*1000 */ {

        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.DAY_OF_YEAR, 0);

        Calendar c3 = Calendar.getInstance(); // today
        c3.add(Calendar.DAY_OF_YEAR, +1);

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(milliseconds);
        DateFormat sdf;


        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds - 570000);
        int hours = calendar2.get(Calendar.HOUR);
        int minutes = calendar2.get(Calendar.MINUTE);
        QuizApp.getPreferenceManager().putString(MyPreferenceManager.QUIZHOUR, String.valueOf(hours));
        QuizApp.getPreferenceManager().putString(MyPreferenceManager.QUIZMIN, String.valueOf(minutes));
        NotificationScheduler.setReminder(HomeActivity.this, AlarmReceiver.class, hours, minutes);

        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {
            sdf = new SimpleDateFormat("hh:mm aaa");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            TimeZone tz = TimeZone.getDefault();
            sdf.setTimeZone(tz);
            return "Today, " + sdf.format(c2.getTime());


        } else if (c3.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c3.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {
            sdf = new SimpleDateFormat("hh:mm aaa");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            TimeZone tz = TimeZone.getDefault();
            sdf.setTimeZone(tz);
            return "Tomorrow, " + sdf.format(c2.getTime());


        } else {
            sdf = new SimpleDateFormat("dd MMM' 'hh:mm aaa");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            TimeZone tz = TimeZone.getDefault();
            sdf.setTimeZone(tz);
        }


        return sdf.format(c2.getTime());
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        if (isConnected) {
            message = "Good! Connected to Internet";
        } else {
            message = "Sorry! Not connected to internet";
            Snackbar snackBar = Snackbar.make(relativeLayout
                    , message, Snackbar.LENGTH_SHORT);
            snackBar.show();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectivityReceiver);
    }
}
