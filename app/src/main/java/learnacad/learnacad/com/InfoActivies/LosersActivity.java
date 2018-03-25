package learnacad.learnacad.com.InfoActivies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import learnacad.learnacad.com.Activities.HomeActivity;
import learnacad.learnacad.com.Activities.LeaderBoardActivity;
import learnacad.learnacad.com.Activities.QuizReviewActivity;
import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.MODELS.Leaderboard;
import learnacad.learnacad.com.MODELS.Winner;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.REST.ApiClients;
import learnacad.learnacad.com.REST.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LosersActivity extends AppCompatActivity {
    @BindViews({R.id.firstimage, R.id.secondimage, R.id.thirdimage})
    CircleImageView[] images;
    @BindViews({R.id.firstext, R.id.secondtext, R.id.thirdtext})
    TextView[] imagetext;

    boolean doubleBackToExitPressedOnce = false;
    String quizid;
    ArrayList<Winner> leaderboardlist;
    private String topic;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losers);
        ButterKnife.bind(this);
        i = getIntent();
        quizid = i.getStringExtra("quizid");
        topic = i.getStringExtra("topic");
        fetchleaderboard(quizid);
        leaderboardlist = new ArrayList<>();

    }

    public void reviewquiz(View view) {

        Intent quiz = new Intent(LosersActivity.this, QuizReviewActivity.class);
        quiz.putExtra("quizid", quizid);
        quiz.putExtra("topic", topic);
        quiz.putExtra("noofquestions", i.getIntExtra("quizquestionno", 5));
        startActivity(quiz);
    }


    public void openleaderboard(View view) {
        Intent leadebaord = new Intent(LosersActivity.this, LeaderBoardActivity.class);
        leadebaord.putExtra("quizid", quizid);
        leadebaord.putExtra("topic", topic);
        startActivity(leadebaord);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
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

    private void fetchleaderboard(String id) {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<Leaderboard> call = apiService.leaderboard(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN),id);
        call.enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {

                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        Leaderboard leaderboard = response.body();
                        leaderboardlist.addAll(leaderboard.getWinners());

                        Log.i("AG", "onResponse: " + response.body());
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        if (leaderboardlist.size() > 0) {
                            if (leaderboardlist.get(0) != null) {
                                imagetext[0].setText(leaderboardlist.get(0).getUserName());
                                Picasso.get().load(leaderboardlist.get(0).getImageUrl()).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.drawable.default_avatar).into(images[0]);
                            }
                            if (leaderboardlist.size() > 1) {
                                if (leaderboardlist.get(1) != null) {
                                    imagetext[1].setText(leaderboardlist.get(1).getUserName());
                                    Picasso.get().load(leaderboardlist.get(1).getImageUrl()).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.drawable.default_avatar).into(images[1]);
                                }
                                if (leaderboardlist.size() > 2) {
                                    if (leaderboardlist.get(2) != null) {
                                        imagetext[2].setText(leaderboardlist.get(2).getUserName());
                                        Picasso.get().load(leaderboardlist.get(2).getImageUrl()).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.drawable.default_avatar).into(images[2]);
                                    }

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

    public void takehome(View view) {
        Intent i = new Intent(LosersActivity.this, HomeActivity.class);
        i.putExtra("token", QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN));
        startActivity(i);
        finish();
    }
}
