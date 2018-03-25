package learnacad.learnacad.com.Activities;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnacad.learnacad.com.Adapters.ConnectivityReceiver;
import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.Adapters.ViewPagerAdapter;
import learnacad.learnacad.com.Adapters.ViewPagerCustomDuration;
import learnacad.learnacad.com.Game2Activity;
import learnacad.learnacad.com.MODELS.Instruction;
import learnacad.learnacad.com.MODELS.Quiz;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.REST.ApiClients;
import learnacad.learnacad.com.REST.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstructionActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    @BindView(R.id.timertextview)
    TextView timertextview;
    @BindView(R.id.noofstudentstextview)
    TextView studentstextview;
    @BindView(R.id.lessthan10seconds)
    TextView final10seconds;
    @BindView(R.id.progressBarcircle)
    RelativeLayout relativeLayout;
    @BindView(R.id.gamestarttextview)
    TextView gamestarttextview;
    @BindView(R.id.pager)
    ViewPagerCustomDuration mPager;
    ViewPagerAdapter mAdapter;
    private Handler handler = new Handler();
    int currentPage = 0;
    String id;
    ArrayList<Instruction> instructions;
    QuizApp application;
    Socket mSocket;
    Intent i;
    Quiz quiz;
    ViewGroup transitionsContainer;
    private boolean doubleBackToExitPressedOnce = false;
    MediaPlayer home, lessthan10;
    AssetFileDescriptor afd1, afd2;
    private boolean connected = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        ButterKnife.bind(this);
        i = getIntent();
        id = i.getStringExtra("_id");
        quiz = (Quiz) i.getSerializableExtra("quiz");
        transitionsContainer = findViewById(R.id.transitions_container);
        application = (QuizApp) getApplication();
        mSocket = application.getSocket(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN));
        playersocket();
        timesocket();
        instructions = new ArrayList<>();
        fetchinstructions();
        moveviewpager();
        home = new MediaPlayer();
        lessthan10 = new MediaPlayer();

        mAdapter = new ViewPagerAdapter(InstructionActivity.this, instructions, 1);
        mPager.setAdapter(mAdapter);
        mPager.setPageMargin(40);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        Shader textShader = new RadialGradient(0, 0, final10seconds.getTextSize(),
                new int[]{Color.parseColor("#ff367d"), Color.parseColor("#ffd500")},
                new float[]{0.4f, 1}, Shader.TileMode.CLAMP);
        final10seconds.getPaint().setShader(textShader);

    }

    private void moveviewpager() {
        new Thread(new Runnable() {
            public void run() {
                while (mPager.getVisibility() == View.VISIBLE) {
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            if (currentPage == mAdapter.getCount())
                                currentPage = 0;
                            mPager.setCurrentItem(currentPage++);
//                            progressStatus = 0;
                        }
                    });
                    try {
                        // Sleep for 8000 milliseconds.
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private void fetchinstructions() {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<List<Instruction>> call = apiService.quizinstructions(quiz.getId());
        call.enqueue(new Callback<List<Instruction>>() {
            @Override
            public void onResponse(Call<List<Instruction>> call, Response<List<Instruction>> response) {

                if (response.isSuccessful()) {
                    List<Instruction> instructionList = response.body();
                    instructions.addAll(instructionList);
                    mAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<Instruction>> call, Throwable t) {
                Log.d("POST_API", t.getCause() + "");
            }
        });
    }

    private void playersocket() {
        mSocket.on("live_people", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                InstructionActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject response = (JSONObject) args[0];
                        Log.d("checkrea", Arrays.toString(args));

                        try {
                            int count = response.getInt("count");
                            studentstextview.setText(count + " Players ");

                        } catch (JSONException e) {

                        }
                    }
                });
            }
        });
    }

    private void timesocket() {
        mSocket.on("time_left", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                InstructionActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject response = (JSONObject) args[0];
                        Log.d("checkrea", Arrays.toString(args));
                        int timeNextQuestionIn;
                        try {
                            timeNextQuestionIn = response.getInt("timeleft");
                            if (timeNextQuestionIn >= 13 && timeNextQuestionIn <= 180) {
                                int min = timeNextQuestionIn / 60;
                                int sec = timeNextQuestionIn % 60;
                                timertextview.setText("Starts in  " + String.format("%02d", min) + ":" + String.format("%02d", sec));
                            } else if (timeNextQuestionIn <= 12) {
                                home.stop();
                                lessthan10.start();

                                TransitionSet set = new TransitionSet()
                                        .addTransition(new Fade())
                                        .addTransition(new Slide())
                                        .setDuration(500)
                                        .setInterpolator(new FastOutLinearInInterpolator());

                                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                                timertextview.setVisibility(View.GONE);
                                mPager.setVisibility(View.GONE);
                                gamestarttextview.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                final10seconds.setText((timeNextQuestionIn - 2) + "");
                                if (timeNextQuestionIn <= 3) {
                                    lessthan10.stop();
                                    Intent i2 = new Intent(InstructionActivity.this, Game2Activity.class);
                                    i2.putExtra("_id", id);
                                    i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    i2.putExtra("quiz", quiz);
                                    startActivity(i2);
                                    finish();
                                    mSocket.off("time_left");
                                    mSocket.off("live_people");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        home.pause();
        lessthan10.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        lessthan10.stop();
        home.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        home = new MediaPlayer();
        lessthan10 = new MediaPlayer();
        try {
            afd1 = getAssets().openFd("home_active_2.mp3");
            afd2 = getAssets().openFd("10_sec_countdown.mp3");
            home.setDataSource(afd1.getFileDescriptor(), afd1.getStartOffset(), afd1.getLength());
            home.prepare();
            home.setLooping(true);
            lessthan10.setDataSource(afd2.getFileDescriptor(), afd2.getStartOffset(), afd2.getLength());
            lessthan10.prepare();
            lessthan10.setLooping(true);
            home.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSnack(boolean isConnected) {
        String message;
        if (isConnected) {
            if (!connected) {
                message = "Good! Connected to Internet";
                mSocket.disconnect();
                mSocket.connect();

                Snackbar snackBar = Snackbar.make(transitionsContainer
                        , message, Snackbar.LENGTH_SHORT);
                snackBar.show();
            }
        } else {
            connected = false;
            message = "Sorry! Not connected to internet";
            Snackbar snackBar = Snackbar.make(transitionsContainer
                    , message, Snackbar.LENGTH_SHORT);
            snackBar.show();

        }


    }
}
