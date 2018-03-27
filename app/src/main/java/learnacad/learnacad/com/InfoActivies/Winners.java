package learnacad.learnacad.com.InfoActivies;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.BuildConfig;
import learnacad.learnacad.com.MODELS.Leaderboard;
import learnacad.learnacad.com.MODELS.Quiz;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.REST.ApiClients;
import learnacad.learnacad.com.REST.ApiInterface;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class Winners extends AppCompatActivity {
    @BindView(R.id.winnerconfetti)
    KonfettiView confetti;
    @BindView(R.id.winnermessage)
    TextView winnermessage;
    @BindView(R.id.winnerusername)
    TextView winnerusername;
    @BindView(R.id.winnerprizetext)
    TextView winnerprizetext;
    @BindView(R.id.winnermoney)
    TextView winnnermoney;
    @BindView(R.id.winneruserimage)
    CircleImageView winnerimage;
    @BindView(R.id.winnerpostion)
    ImageView winnerpostion;

    private boolean isAllowedToRead;
    String quizid;
    Quiz quiz;
    MediaPlayer winner;
    AssetFileDescriptor afd1;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners);
        ButterKnife.bind(this);
        if (!checkPermission()) {
            requestPermission();
        }
        Intent i = getIntent();
        quiz = (Quiz) i.getSerializableExtra("quiz");
        quizid = quiz.getId();
        Log.i("TAG", "onCreate: quizid" + quizid);
        if (i.getBooleanExtra("iseliminated", false) || i.getBooleanExtra("islate", false)) {
            Intent loseractivity = new Intent(Winners.this, LosersActivity.class);
            loseractivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            loseractivity.putExtra("quizid", quizid);
            Log.i("TAG", "onCreate: " + quiz.getQuestions().size());
            loseractivity.putExtra("quizquestionno", quiz.getQuestions().size());
            loseractivity.putExtra("topic", quiz.getTopic());
            startActivity(loseractivity);
            finish();
        } else {
            winner = new MediaPlayer();
            try {
                afd1 = getAssets().openFd("winner.mp3");
                winner.setDataSource(afd1.getFileDescriptor(), afd1.getStartOffset(), afd1.getLength());
                winner.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            fetchleaderboard(quizid);
        }

    }

    private void fetchleaderboard(String id) {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<Leaderboard> call = apiService.leaderboard(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN), id);
        call.enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {

                if (response.isSuccessful()) {
                    Leaderboard leaderboards = response.body();
                    if (leaderboards.getWinners().size() > 0) {
                        if (leaderboards.getWinners().get(0).getUserName().equals(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME))) {
                            winnerusername.setText(leaderboards.getWinners().get(0).getUserName());
                            winnermessage.setText("Congratulations");
                            setdata(0);
                        } else if (leaderboards.getWinners().get(1).getUserName().equals(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME))) {
                            winnerusername.setText(leaderboards.getWinners().get(1).getUserName());
                            winnermessage.setText("Congratulations");
                            setdata(1);
                        } else if (leaderboards.getWinners().get(2).getUserName().equals(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME))) {
                            winnerusername.setText(leaderboards.getWinners().get(2).getUserName());
                            winnermessage.setText("Congratulations");
                            setdata(2);
                        } else {
                            for (int i = 3; i < leaderboards.getWinners().size(); i++) {
                                if (leaderboards.getWinners().get(i).getUserName().equals(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME))) {
                                    winnermessage.setText("Well Done");
                                    winnnermoney.setVisibility(View.INVISIBLE);
                                    winnerpostion.setVisibility(View.GONE);
                                    position = i +1;
                                    winnerusername.setText(leaderboards.getWinners().get(i).getUserName());
                                    Picasso.get().load(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERIMAGE)).into(winnerimage);
                                }

                            }
                        }
                    }


                }
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {
                Log.d("POST_API", t.getCause() + "");
            }
        });
    }

    private void setdata(int pos) {
        winner.start();
        if (pos == 0) {
            position = pos + 1;
            winnerpostion.setImageResource(R.drawable.winner_first_circle);
            winnnermoney.setText("₹" + quiz.getPrize().get(0).getPrize());
        } else if (pos == 1) {
            position = pos + 1;
            winnerpostion.setImageResource(R.drawable.winner_second_circle);
            winnnermoney.setText("₹" + quiz.getPrize().get(1).getPrize());
        } else if (pos == 2) {
            position = pos + 1;
            winnerpostion.setImageResource(R.drawable.winner_third_circle);
            winnnermoney.setText("₹" + quiz.getPrize().get(2).getPrize());
        }
        Picasso.get().load(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERIMAGE)).into(winnerimage);

        confetti.build()
                .addColors(Color.parseColor("#FCE18B"), Color.parseColor("#FF736E"), Color.parseColor("#B68CF1"), Color.parseColor("#F4316C"), Color.parseColor("#006DEA"), Color.parseColor("#39BA9E"), Color.parseColor("#BC3D73"))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 16f))
                .setPosition(-50f, confetti.getWidth() + 50f, -50f, confetti.getHeight() + 50f)
                .stream(300, 5000L);


    }


    public void sharescreenshot(View view) {

        if (isAllowedToRead) {
            Toast.makeText(Winners.this, "Screenshot Saved", Toast.LENGTH_SHORT).show();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = takeScreenshot();
                    saveBitmap(bitmap);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 500);
        } else {
            if (!checkPermission()) {
                requestPermission();
            }
        }

    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
        shareImage(imagePath);
    }

    private void shareImage(File file) {
        Uri uri = FileProvider.getUriForFile(Winners.this, BuildConfig.APPLICATION_ID + ".provider", file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        if (position <= 3)
            intent.putExtra(Intent.EXTRA_TEXT, "Hey, I am playing live quizzes on Quizrr and winning cash prizes.\n" +
                    "\n" +
                    "Download Quizrr from http://bit.ly/playquizrr. To earn a free lifeline, join using my referral code \"" + QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME)+"\"");
        else
            intent.putExtra(Intent.EXTRA_TEXT, "Look, I just answered all the questions correctly on Quizrr.\n" +
                    "\n" +
                    "Play live quizzes and win cash prizes.Download Quizrr from http://bit.ly/playquizrr. To earn a free lifeline, join using my referral code \"" + QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME)+"\"");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(Winners.this, "No App Available", Toast.LENGTH_SHORT).show();
        }

    }


    private void requestPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, 2501);
        }
    }

    private boolean checkPermission() {

        int readSMS = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);


        isAllowedToRead = (readSMS == PackageManager.PERMISSION_GRANTED);
        return isAllowedToRead;

    }


    public void winning(View view) {
        Intent i = new Intent(Winners.this, WinningActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.putExtra("quizid", quizid);
        i.putExtra("topic", quiz.getTopic());
        i.putExtra("quizquestionno", quiz.getQuestions().size());
        startActivity(i);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        winner.stop();
    }
}
