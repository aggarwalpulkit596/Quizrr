package learnacad.learnacad.com.InfoActivies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.BindViews;
import de.hdodenhof.circleimageview.CircleImageView;
import learnacad.learnacad.com.Activities.HomeActivity;
import learnacad.learnacad.com.Activities.QuizReviewActivity;
import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.Activities.LeaderBoardActivity;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;


public class WinningActivity extends AppCompatActivity {


    boolean doubleBackToExitPressedOnce = false;
    String quizid;
    private String topic;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);
        i = getIntent();
        quizid = i.getStringExtra("quizid");
        topic = i.getStringExtra("topic");

        Log.i("TAG", "onCreate: quizid" + quizid);


    }

    public void reviewquiz(View view) {

        Intent quiz = new Intent(WinningActivity.this, QuizReviewActivity.class);
        quiz.putExtra("quizid", quizid);
        quiz.putExtra("noofquestions", i.getIntExtra("quizquestionno", 5));
        quiz.putExtra("topic", topic);
        startActivity(quiz);
    }


    public void openleaderboard(View view) {
        Intent leaderboard = new Intent(WinningActivity.this, LeaderBoardActivity.class);
        leaderboard.putExtra("quizid", quizid);
        leaderboard.putExtra("topic", topic);
        startActivity(leaderboard);
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

    public void backbtn(View view) {
        onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void takehome(View view) {
        Intent i = new Intent(WinningActivity.this, HomeActivity.class);
        i.putExtra("token", QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN));
        startActivity(i);
        finish();
    }

    public void winning(View view) {
        Intent i = new Intent(WinningActivity.this, HomeActivity.class);
        i.putExtra("token", QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN));
        startActivity(i);
        finish();
    }
}
