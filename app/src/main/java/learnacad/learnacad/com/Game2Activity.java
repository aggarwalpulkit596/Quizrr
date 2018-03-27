package learnacad.learnacad.com;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.captain_miao.optroundcardview.OptRoundCardView;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import katex.hourglass.in.mathlib.MathView;
import learnacad.learnacad.com.Adapters.AlarmReceiver;
import learnacad.learnacad.com.Adapters.ConnectivityReceiver;
import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.Adapters.NotificationScheduler;
import learnacad.learnacad.com.InfoActivies.Winners;
import learnacad.learnacad.com.MODELS.Quiz;
import learnacad.learnacad.com.MODELS.Response;

public class Game2Activity extends AppCompatActivity implements View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {


    @BindView(R.id.transitions_container)
    LinearLayout transitionsContainer;


    //Questionview Binding
    @BindView(R.id.questionactivity)
    RelativeLayout questionlayout;
    @BindView(R.id.questiontimespentvalue)
    TextView questiontimespent;
    @BindView(R.id.questionnoofplayerstextview)
    TextView questionnofplayers;
    @BindView(R.id.questionnumbertextview)
    TextView questionnumbertextview;
    @BindView(R.id.questionprogressbar)
    ProgressBar questionprogressbar;
    @BindView(R.id.questionwebview1)
    MathView gamequestiontext;
    @BindViews({R.id.questionanswebview1, R.id.questionanswebview2, R.id.questionanswebview3, R.id.questionanswebview4})
    MathView[] questionoptionstext;
    @BindViews({R.id.questioncardView2, R.id.questioncardView3, R.id.questioncardView4, R.id.questioncardView5})
    OptRoundCardView[] questionbackground;
    @BindViews({R.id.optionimageA, R.id.optionimageB, R.id.optionimageC, R.id.optionimageD})
    ImageView[] optionletter;
    @BindView(R.id.gameinfobtn)
    ImageView infobutton;
    @BindView(R.id.questionmode)
    LinearLayout questionmode;

    //Gif Layout
    @BindView(R.id.gifactivtity)
    RelativeLayout giflayout;
    @BindView(R.id.gifimage)
    SimpleExoPlayerView gifImageView;
    SimpleExoPlayer player;
    @BindView(R.id.gifttext)
    TextView giftext;

    //lateLayout;
    @BindView(R.id.latelayout)
    RelativeLayout latelayout;
    @BindView(R.id.latenextquestiontimer)
    TextView latenextquestiontime;
    @BindView(R.id.latenoofplayerstextview)
    TextView latenoofplayer;


    //Responseview Binding
    @BindView(R.id.responseactivity)
    RelativeLayout responselayout;
    @BindView(R.id.responsenextquestiontimer)
    TextView responsequestiontimer;
    @BindView(R.id.responsenoofplayerstextview)
    TextView responsenoofplayers;
    @BindView(R.id.responsenextquestiontimertextview)
    TextView responsequestimertextview;
    @BindView(R.id.responseimageview)
    ImageView responseimageview;
    @BindView(R.id.responsemesaagetextview)
    TextView gameresponsemessage;
    @BindView(R.id.responsetitletextview)
    TextView gameresponsetitle;
    @BindView(R.id.responseyouranswertime)
    TextView gameyouranswertime;
    @BindView(R.id.responsequickestanswer)
    TextView gamequickanswer;
    @BindView(R.id.responseheartimage)
    ImageView heartimage;
    @BindViews({R.id.responseansprogress1, R.id.responseansprogress2, R.id.responseansprogress3, R.id.responseansprogress4})
    ProgressBar[] responseprogressview;
    @BindViews({R.id.responseansbackground1, R.id.responseansbackground2, R.id.responseansbackground3, R.id.responseansbackground4})
    RelativeLayout[] responseansbackground;
    @BindViews({R.id.responsecardview2, R.id.responsecardview3, R.id.responsecardview4, R.id.responsecardview5})
    OptRoundCardView[] responsebackground;
    @BindViews({R.id.responseanswebview1, R.id.responseanswebview2, R.id.responseanswebview3, R.id.responseanswebview4})
    TextView[] responsetextview;
    @BindViews({R.id.responseimageA, R.id.responseimageB, R.id.responseimageC, R.id.responseimageD})
    ImageView[] responseletter;

    //Variables
    private Handler handler = new Handler();
    int progressbarstatus = 0;
    CountDownTimer timer;
    private CountDownTimer timer2;
    boolean doubleBackToExitPressedOnce = false;
    TransitionSet set;
    String timeleft;
    QuizApp application;
    Socket mSocket;
    String LIVE_GAME_STATUS = "QUESTION_SHOW", USER_GAME_STATUS;
    private boolean isLifeLineUsed, isEliminated = false;
    String id, quizid;
    String timespent;
    Integer timeofquestion, timebetweenquestion;
    static int number = 1;
    Boolean islate;
    Quiz quiz;
    private int selectedoption;
    private boolean firsttime = false;
    private boolean isover;
    private boolean islatefirsttime;
    String sessionoverurl;
    MediaPlayer question_pop, queslessthan5, correct, lifev1, lifev2, nolife, gameover;
    AssetFileDescriptor afd1, afd2, afd3, afd4, afd5, afd6, afd7;
    ConnectivityReceiver connectivityReceiver;
    private boolean connected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        ButterKnife.bind(this);
        Intent i = getIntent();
        application = (QuizApp) getApplication();
        mSocket = application.getSocket(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN));


        quiz = (Quiz) i.getSerializableExtra("quiz");
        if (quiz != null) {
            timebetweenquestion = quiz.getTimeBetweenQuestion();
            timeofquestion = quiz.getTimeOfQuestion();
            quizid = quiz.getId();
            islate = quiz.getIsLate();
            if (islate) {
                Log.i("TAG", "onCreate: " + islatefirsttime);
                islatefirsttime = false;
                number = quiz.getIndex();
                questionlayout.setVisibility(View.GONE);
                latelayout.setVisibility(View.VISIBLE);
                firsttime = true;
            } else {
                Log.i("TAG", "onCreate:1 " + islatefirsttime);
                islatefirsttime = true;
            }
        }
        set = new TransitionSet()
                .addTransition(new Slide())
                .setDuration(2000)
                .setInterpolator(new FastOutLinearInInterpolator());

        questionsocket();
        responsesocket();
        timesocket();
        playersocket();
        sessionsocket();
    }

    private void sessionsocket() {
        mSocket.on("session_over", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

                Game2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isover = true;
                        Log.d("checkrea", args + "");
                        Object[] response = args;
                        sessionoverurl = response.toString();
                        showResponseLayout();
                        mSocket.off("session_over");
                    }
                });
            }
        }).on("error", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

                Game2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("checkrea", Arrays.toString(args));

                        Toast.makeText(Game2Activity.this, "" + args[0].toString(), Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });

    }

    private void timesocket() {
        mSocket.on("time_left", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Game2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject response = (JSONObject) args[0];
                        Log.d("checkrea", Arrays.toString(args));
                        String timeNextQuestionIn;
                        try {
                            timeleft = response.getString("timeleft");
                            timeNextQuestionIn = response.getString("nextQuestionIn");
                            if (LIVE_GAME_STATUS.contentEquals("QUESTION_SHOW")) {

                                if (islate && !islatefirsttime) {
                                    Log.i("TAG", "onCreate2: " + islatefirsttime);
                                    if (timeNextQuestionIn.equals("1")) {
                                        islatefirsttime = true;
                                    }
                                    latequestiontimer(timeNextQuestionIn);
                                } else if (timeNextQuestionIn.equals(String.valueOf(timebetweenquestion))) {
                                    nextquestiontimer(timebetweenquestion);
                                    showResponseLayout();
                                }
                            }


                            if (LIVE_GAME_STATUS.contentEquals("RESPONSE_SCREEN")) {
                                if (timeNextQuestionIn.contentEquals("1")) {

                                }
                            }
                        } catch (JSONException e) {

                        }
                    }
                });
            }
        });
    }


    private void questionsocket() {
        mSocket.on("get_question", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {


                LIVE_GAME_STATUS = "QUESTION_SHOW";

                Game2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject response = (JSONObject) args[0];
                        try {
                            Log.i("TAG", "showQuestionLayout: true" + args[0]);


                            Log.i("TAG", "run+game: " + args[0]);
                            USER_GAME_STATUS = response.getString("game_status");
                            String questionStr = response.getString("questionText");
                            id = response.getString("_id");
                            JSONArray options = response.getJSONArray("options");
                            JSONObject optionAObj = (JSONObject) options.get(0);
                            String optionA = optionAObj.getString("value");

                            JSONObject optionBObj = (JSONObject) options.get(1);
                            String optionB = optionBObj.getString("value");

                            JSONObject optionCObj = (JSONObject) options.get(2);
                            String optionC = optionCObj.getString("value");

                            JSONObject optionDObj = (JSONObject) options.get(3);
                            String optionD = optionDObj.getString("value");

                            gamequestiontext.setDisplayText(questionStr);
                            gamequestiontext.setTextSize(18);
                            questionoptionstext[0].setDisplayText(optionA);
                            questionoptionstext[0].setTextSize(15);
                            questionoptionstext[1].setDisplayText(optionB);
                            questionoptionstext[1].setTextSize(15);
                            questionoptionstext[2].setDisplayText(optionC);
                            questionoptionstext[2].setTextSize(15);
                            questionoptionstext[3].setDisplayText(optionD);
                            questionoptionstext[3].setTextSize(15);
                            questionnumbertextview.setText(number++ + "");
                            latelayout.setVisibility(View.GONE);
                            responselayout.setVisibility(View.GONE);
                            questionlayout.setVisibility(View.VISIBLE);
                            showQuestionLayout();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("TAG", "run: " + e);

                        }
                    }
                });

            }
        });
    }

    private void responsesocket() {
        mSocket.on("questionDetails", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Game2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject response = (JSONObject) args[0];
                        Log.d("checkrea1", Arrays.toString(args));

                        try {
                            USER_GAME_STATUS = response.getString("state");
                            String option1_Count = response.getString("option1_count");
                            String option2_Count = response.getString("option2_count");
                            String option3_Count = response.getString("option3_count");
                            String option4_Count = response.getString("option4_count");
                            String correct_option = response.getString("correct_option");
                            String lease_time_Taken = response.getString("least_time_taken");
                            String url = response.getString("gifUrl");

                            if (USER_GAME_STATUS.contentEquals("active1")) {
                                Response r = new Response(USER_GAME_STATUS, Integer.parseInt(option1_Count), Integer.parseInt(option2_Count), Integer.parseInt(option3_Count), Integer.parseInt(option4_Count), Integer.parseInt(correct_option), lease_time_Taken, url);
                                setreponseview(r);
                            } else if (USER_GAME_STATUS.contentEquals("active2")) {
                                isLifeLineUsed = true;
                                Response r = new Response(USER_GAME_STATUS, Integer.parseInt(option1_Count), Integer.parseInt(option2_Count), Integer.parseInt(option3_Count), Integer.parseInt(option4_Count), Integer.parseInt(correct_option), lease_time_Taken, url);
                                setreponseview(r);

                            } else if (USER_GAME_STATUS.contentEquals("inactive1")) {
                                isEliminated = true;
                                Response r = new Response(USER_GAME_STATUS, Integer.parseInt(option1_Count), Integer.parseInt(option2_Count), Integer.parseInt(option3_Count), Integer.parseInt(option4_Count), Integer.parseInt(correct_option), lease_time_Taken, url);
                                setreponseview(r);


                            } else if (USER_GAME_STATUS.contentEquals("inactive2")) {
                                isEliminated = true;
                                Response r = new Response(USER_GAME_STATUS, Integer.parseInt(option1_Count), Integer.parseInt(option2_Count), Integer.parseInt(option3_Count), Integer.parseInt(option4_Count), Integer.parseInt(correct_option), lease_time_Taken, url);
                                setreponseview(r);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                });
            }
        });

    }

    private void playersocket() {
        mSocket.on("live_people", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Game2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject response = (JSONObject) args[0];
                        try {
                            int count = response.getInt("count");
                            questionnofplayers.setText(count + " Players ");
                            responsenoofplayers.setText(count + " Players");
                            latenoofplayer.setText(count + " Players");
                        } catch (JSONException e) {

                        }
                    }
                });
            }
        });
    }

    private void showResponseLayout() {
        if (islatefirsttime) {

//            TransitionManager.beginDelayedTransition(transitionsContainer, set);
            giflayout.setVisibility(View.VISIBLE);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Log.d("TAG", "run() called");
                    giflayout.setVisibility(View.GONE);

                    gifImageView.setVisibility(View.VISIBLE);
                    responselayout.setVisibility(View.VISIBLE);

                }
            }, 7000);


            questionlayout.setVisibility(View.GONE);
            LIVE_GAME_STATUS = "RESPONSE_SCREEN";
            if (isover) {
                responsequestiontimer.setVisibility(View.INVISIBLE);
                responsequestimertextview.setVisibility(View.INVISIBLE);
                handler.postDelayed(new Runnable() {

                    @Override

                    public void run() {
                        gameover.start();
//                        TransitionManager.beginDelayedTransition(transitionsContainer, set);
                        responselayout.setVisibility(View.GONE);

                        Uri uri = Uri.parse("http://res.cloudinary.com/mathongo/image/upload/v1521801701/anxious.mp4");
                        MediaSource mediaSource = buildMediaSource(uri);
                        LoopingMediaSource loopingSource = new LoopingMediaSource(mediaSource);
                        player.prepare(loopingSource, true, false);
                        giflayout.setVisibility(View.VISIBLE);
                        giftext.setText("Hold your breath.\nWe’re finding the winners...");
                        mSocket.disconnect();
                        giflayout.setBackground(getResources().getDrawable(R.drawable.custom_background));
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(Game2Activity.this, Winners.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                                i.putExtra("quiz", quiz);
                                i.putExtra("iseliminated", isEliminated);
                                i.putExtra("islate", islate);
                                startActivity(i);
                                finish();
                            }
                        }, 5000);

                    }
                }, timebetweenquestion * 1000);

            }
        } else {
            Log.i("TAG", "onCreate:4 " + islatefirsttime);

            latelayout.setVisibility(View.VISIBLE);
            islatefirsttime = true;

            setlatesetting();


        }
    }

    private void setlatesetting() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.dialog_late, null);
        final AlertDialog infodialog = new AlertDialog.Builder(this).create();
        infodialog.setView(deleteDialogView);
        infodialog.getWindow().setBackgroundDrawableResource(R.drawable.menubackground);
        deleteDialogView.findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infodialog.dismiss();

            }
        });
        deleteDialogView.findViewById(R.id.btn_reminder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Game2Activity.this, "Reminder Set !!!!", Toast.LENGTH_SHORT).show();
                NotificationScheduler.setReminder(Game2Activity.this, AlarmReceiver.class, 21, 00);

            }
        });

        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infodialog.show();
            }
        });
    }


    private void showQuestionLayout() {

        if (isEliminated) {
            setcards(true);
        } else if (islate) {
            setcards(true);
        } else {
            {
                settimer(timeofquestion);
                setcards(false);
            }
        }
        question_pop.start();
        moveprogressbar(timeofquestion);
    }

    private void setreponseview(final Response status) {
        if (status.getUser_game_status().equals("inactive2")) {
            inactive2(status);
        } else if (status.getUser_game_status().equals("inactive1")) {
            inactive1(status);
        } else if (status.getUser_game_status().equals("active1")) {
            active1(status);
        } else if (status.getUser_game_status().equals("active2")) {
            active2(status);
        }

    }

    private void active1(Response status) {
        correct.start();
        gameresponsetitle.setText("Yay! You got this one.");
        gameresponsemessage.setVisibility(View.GONE);
        heartimage.setVisibility(View.GONE);
        gameyouranswertime.setText(timespent.toString() + "s");
        responseimageview.setImageResource(R.drawable.check_icon);
        responseimageview.setBackground(getResources().getDrawable(R.drawable.response_correct));
        gamequickanswer.setText(status.getLeast_time_taken() + "s");
        giftext.setText("Correct");
        giflayout.setBackgroundColor(Color.parseColor("#40c057"));


//        Glide.with(Game2Activity.this).asGif().load(status.getGifUrl()).into(gifImageView);
        setattemptedprogress(status, selectedoption);

    }

    private void active2(Response status) {
        lifev1.start();
        gameresponsetitle.setText("Oops! You missed it.");
        gameresponsemessage.setVisibility(View.VISIBLE);
        heartimage.setVisibility(View.VISIBLE);
        gameresponsemessage.setText("But lifeline saved you.");
        gameyouranswertime.setText(timespent.toString() + "s");
        responseimageview.setImageResource(R.drawable.x_icon);
        responseimageview.setBackground(getResources().getDrawable(R.drawable.response_lifeline));
        gamequickanswer.setText(status.getLeast_time_taken() + "s");
        giftext.setText("Incorrect!\nBut lifeline saved you.");
        giflayout.setBackgroundColor(Color.parseColor("#2e005b"));

//        Glide.with(Game2Activity.this).asGif().load(status.getGifUrl()).into(gifImageView);
        setattemptedprogress(status, selectedoption);
    }


    private void inactive1(final Response status) {
        nolife.start();
        gameresponsemessage.setVisibility(View.VISIBLE);
        gameresponsetitle.setText("Oops! You missed it.");
        heartimage.setVisibility(View.VISIBLE);
        heartimage.setAlpha(0.4f);
        if(isLifeLineUsed){
            gameresponsemessage.setText("Lifeline already used.");
        }else
        gameresponsemessage.setText("No lifeline to save you.");
        gameyouranswertime.setText(timespent.toString() + "s");
        responseimageview.setImageResource(R.drawable.x_icon);
        responseimageview.setBackground(getResources().getDrawable(R.drawable.response_lifeline));
        giftext.setText("You’ve been eliminated!");
        giflayout.setBackgroundColor(Color.parseColor("#d63f3a"));

//        Glide.with(Game2Activity.this).asGif().load(status.getGifUrl()).into(gifImageView);
        gamequickanswer.setText(status.getLeast_time_taken() + "s");
        Log.i("TAG", "changeselectedview: " + selectedoption);
        setattemptedprogress(status, selectedoption);
        firsttime = true;

    }

    private void inactive2(final Response status) {
        if (isEliminated && firsttime) {
            gameresponsetitle.setText("Viewer Mode is on.");
            responseimageview.setImageResource(R.drawable.eye_icon);
            gameresponsemessage.setText("You’ve been eliminated.");
            giftext.setText("You’ve been eliminated!");
            responseimageview.setBackground(getResources().getDrawable(R.drawable.response_eliminated));
        } else if (islate && firsttime) {
            gameresponsetitle.setText("Viewer Mode is on.");
            nolife.start();
            responseimageview.setImageResource(R.drawable.eye_icon);
            gameresponsemessage.setText("You joined the game late.");
            giftext.setText("You joined late./nBe on time");
            responseimageview.setBackground(getResources().getDrawable(R.drawable.response_eliminated));
        } else {
            firsttime = true;
            gameresponsetitle.setText("Tick! Tock! Times Up.");
            nolife.start();
            gameresponsemessage.setText("You’ve been eliminated.");
            giftext.setText("You’ve been eliminated!");
            responseimageview.setImageResource(R.drawable.clock_icon);
            responseimageview.setBackground(getResources().getDrawable(R.drawable.response_eliminated));
        }
        gameresponsemessage.setVisibility(View.VISIBLE);
        giflayout.setBackgroundColor(Color.parseColor("#d63f3a"));
        heartimage.setVisibility(View.GONE);
//        Glide.with(Game2Activity.this).asGif().load(status.getGifUrl()).into(gifImageView);
        gameyouranswertime.setText("-");
        gamequickanswer.setText(status.getLeast_time_taken() + "s");
        setattemptedprogress(status, -1);
    }

    private void setattemptedprogress(final Response status, int optionselected) {

        Uri uri = Uri.parse(status.getGifUrl());
        MediaSource mediaSource = buildMediaSource(uri);
        LoopingMediaSource loopingSource = new LoopingMediaSource(mediaSource);
        player.prepare(loopingSource, true, false);

        TypedArray img, img2;
        img = getResources().obtainTypedArray(R.array.selectedletter);
        img2 = getResources().obtainTypedArray(R.array.defaultletter);

        int[] values = {status.getOption1_count(), status.getOption2_count(), status.getOption3_count(), status.getOption4_count()};
        int totalcount = status.getOption1_count() + status.getOption2_count() + status.getOption3_count() + status.getOption4_count();
        for (int i = 0; i < 4; i++) {
            responseletter[i].setImageResource(img2.getResourceId(i, 0));
            responsebackground[i].setBackgroundColor(Color.parseColor("#ffffff"));
            responseprogressview[i].setProgress(values[i] * 0);
            responseprogressview[i].setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_response_states));

        }
        responsetextview[0].setText(status.getOption1_count() + " Attempted");
        responsetextview[1].setText(status.getOption2_count() + " Attempted");
        responsetextview[2].setText(status.getOption3_count() + " Attempted");
        responsetextview[3].setText(status.getOption4_count() + " Attempted");
        for (int i = 0; i < 4; i++) {
            responseprogressview[i].setMax(totalcount * 1000);
            responseprogressview[i].setProgress(values[i] * 1000);
            responseprogressview[status.getCorrect_option() - 1].setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_correct_response_states));
            ProgressBarAnimation anim = new ProgressBarAnimation(responseprogressview[i], 0, values[i] * 1000);
            anim.setDuration(1500);
            responseprogressview[i].startAnimation(anim);
        }
        if (optionselected != -1) {
            responseletter[optionselected].setImageResource(img.getResourceId(optionselected, 0));
            responsebackground[optionselected].setBackgroundColor(Color.parseColor("#e9e0f2"));
        }

    }

    private void settimer(final Integer timeleft) {

        timer = new CountDownTimer(timeleft * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (timeleft - millisUntilFinished / 1000);
                seconds = seconds % 60;
                questiontimespent.setText(String.format(String.format("%02d", seconds)) + " sec");
                if (seconds > (timeofquestion - 10) && seconds <= timeofquestion)
                    queslessthan5.start();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void nextquestiontimer(final Integer timeleft) {

        timer2 = new CountDownTimer(timeleft * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                seconds = seconds % 60;

                responsequestiontimer.setText(String.format(String.format("%02d", seconds)) + " sec");
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void latequestiontimer(String timeleft) {
        latenextquestiontime.setText(timeleft + " sec");
    }


    private void moveprogressbar(final Integer value) {

        questionprogressbar.setMax(value * 99);
        progressbarstatus = value * 99;

        new Thread(new Runnable() {
            public void run() {
                while (progressbarstatus >= 2) {
                    progressbarstatus -= 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            questionprogressbar.setProgress(progressbarstatus);
                        }
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!isEliminated)
                    if (!islate)
                        timer.cancel();
            }
        }).start();
    }

    private void setcards(boolean b) {
        TypedArray img;
        img = getResources().obtainTypedArray(R.array.defaultletter);
        for (int i = 0; i < 4; i++) {
            questiontimespent.setText("00 sec");
            optionletter[i].setImageResource(img.getResourceId(i, 0));
            questionbackground[i].setCardBackgroundColor(Color.parseColor("#ffffff"));
            if (b) {
                questiontimespent.setAlpha(0.3f);
                optionletter[i].setAlpha(0.5f);
                questionoptionstext[i].setAlpha(0.5f);
                questionbackground[i].setOnClickListener(null);
                questionoptionstext[i].setTextColor(Color.parseColor("#992a2436"));
                questionmode.setVisibility(View.VISIBLE);
                if (islate) {
                    setlatesetting();
                } else
                    seteliminatedsetting();

            } else {
                questionmode.setVisibility(View.GONE);
                questionbackground[i].setOnClickListener(this);
                questionoptionstext[i].setClickable(true);

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.questioncardView2:
                changeselectedview(0);
                break;
            case R.id.questioncardView3:
                changeselectedview(1);
                break;
            case R.id.questioncardView4:
                changeselectedview(2);
                break;
            case R.id.questioncardView5:
                changeselectedview(3);
                break;
        }

    }

    private void changeselectedview(int i) {
        TypedArray img;
        img = getResources().obtainTypedArray(R.array.selectedletter);
        for (int x = 0; x < 4; x++) {
            if (x == i) {
                optionletter[x].setImageResource(img.getResourceId(x, 0));
                questionbackground[x].setCardBackgroundColor(Color.parseColor("#e9e0f2"));
                questionbackground[x].setOnClickListener(null);
            } else {
                questionbackground[x].setOnClickListener(null);
            }
        }

        timer.cancel();
        timespent = questiontimespent.getText().toString().substring(0, questiontimespent.getText().length() - 4);
        selectedoption = i;
        Log.i("TAG", "changeselectedview: " + selectedoption);
        sendResponse(i + 1, timespent);

    }

    private void seteliminatedsetting() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.dialog_eliminated, null);
        final AlertDialog infodialog = new AlertDialog.Builder(this).create();
        infodialog.setView(deleteDialogView);
        infodialog.getWindow().setBackgroundDrawableResource(R.drawable.menubackground);
        deleteDialogView.findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infodialog.dismiss();

            }
        });
        deleteDialogView.findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(Intent.EXTRA_TEXT, "Hey, I am playing live quizzes on Quizrr and winning cash prizes.\n" +
                        "\n" +
                        "Download Quizrr from http://bit.ly/playquizrr. To earn a free lifeline, join using my referral code \"" + QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME)+"\"");
                try {
                    startActivity(shareintent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Game2Activity.this, "No App Available", Toast.LENGTH_SHORT).show();
                }

            }
        });

        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infodialog.show();
            }
        });

    }

    private void sendResponse(int option, String timeTaken) {


        JSONObject toSend = new JSONObject();
        try {
            toSend.put("Answer", option);
            toSend.put("timeTaken", timeTaken);
            toSend.put("_id", id);
        } catch (JSONException e) {
            e.printStackTrace();

        }
        mSocket.emit("answer_question", toSend);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);

        /*register connection status listener*/
        QuizApp.getInstance().setConnectivityListener(this);
        musicbinding();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    private void musicbinding() {
        question_pop = new MediaPlayer();
        queslessthan5 = new MediaPlayer();
        correct = new MediaPlayer();
        lifev1 = new MediaPlayer();
        lifev2 = new MediaPlayer();
        nolife = new MediaPlayer();
        gameover = new MediaPlayer();
        try {
            afd1 = getAssets().openFd("question_pop.mp3");
            afd2 = getAssets().openFd("last_10_sec.mp3");
            afd4 = getAssets().openFd("incorrect_with_lifeline.mp3");
            afd3 = getAssets().openFd("correct.mp3");
            afd5 = getAssets().openFd("incorrect_with_lifeline_v2.mp3");
            afd6 = getAssets().openFd("incorrect_without_lifeline.mp3");
            afd7 = getAssets().openFd("game_over.mp3");

            question_pop.setDataSource(afd1.getFileDescriptor(), afd1.getStartOffset(), afd1.getLength());
            queslessthan5.setDataSource(afd2.getFileDescriptor(), afd2.getStartOffset(), afd2.getLength());
            correct.setDataSource(afd3.getFileDescriptor(), afd3.getStartOffset(), afd3.getLength());
            lifev1.setDataSource(afd4.getFileDescriptor(), afd4.getStartOffset(), afd4.getLength());
            lifev2.setDataSource(afd5.getFileDescriptor(), afd5.getStartOffset(), afd5.getLength());
            nolife.setDataSource(afd6.getFileDescriptor(), afd6.getStartOffset(), afd6.getLength());
            gameover.setDataSource(afd7.getFileDescriptor(), afd7.getStartOffset(), afd7.getLength());


            question_pop.prepare();
            queslessthan5.prepare();
            correct.prepare();
            lifev1.prepare();
            lifev2.prepare();
            nolife.prepare();
            gameover.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float to;

        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }

    }

    private void showSnack(boolean isConnected) {
        String message;
        if (isConnected) {
            if (!connected) {
                message = "Good! Connected to Internet";

                mSocket.disconnect();
                mSocket.connect();
                isEliminated = true;
                firsttime = true;

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

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectivityReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        question_pop.stop();
        correct.stop();
        lifev1.stop();
        lifev2.stop();
        nolife.stop();
        gameover.stop();

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

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());

        gifImageView.setPlayer(player);
        player.setPlayWhenReady(true);

    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }
}
