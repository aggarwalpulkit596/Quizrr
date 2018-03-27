package quizrr.quizrr.quizrr.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import quizrr.quizrr.quizrr.Adapters.Leaderboardlistadapter;
import quizrr.quizrr.quizrr.Adapters.MyPreferenceManager;
import quizrr.quizrr.quizrr.MODELS.Leaderboard;
import quizrr.quizrr.quizrr.MODELS.Winner;
import quizrr.quizrr.quizrr.QuizApp;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.REST.ApiClients;
import quizrr.quizrr.quizrr.REST.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoardActivity extends AppCompatActivity {

    ArrayList<Winner> leaderboardlist;
    Leaderboardlistadapter mAdapter;

    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.leaderboardrootlayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.tasklayout)
    LinearLayout snackbar;
    @BindView(R.id.nametextview)
    TextView username;
    @BindView(R.id.timetextview)
    TextView usertime;
    @BindView(R.id.positiontextvieww)
    TextView userposition;
    @BindView(R.id.topictextview)
    TextView topic;
    @BindViews({R.id.firstimage, R.id.secondimage, R.id.thirdimage})
    CircleImageView[] images;
    @BindViews({R.id.firstext, R.id.secondtext, R.id.thirdtext})
    TextView[] imagetext;
    ProgressBar progressBar;
    String quizid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        quizid = intent.getStringExtra("quizid");
        topic.setText(intent.getStringExtra("topic"));
        Log.i("TAG", "onCreate: quizid" + quizid);
        progressBar = new ProgressBar(LeaderBoardActivity.this, null, android.R.attr.progressBarStyleLargeInverse);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(progressBar, params);
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);//To show ProgressBar
        leaderboardlist = new ArrayList<>();
        fetchleaderboard(quizid);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listView, false);
        listView.addHeaderView(header, null, false);
        mAdapter = new Leaderboardlistadapter(this, leaderboardlist);
        listView.setAdapter(mAdapter);

    }

    private void fetchleaderboard(String quizid) {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<Leaderboard> call = apiService.leaderboard(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN),quizid);
        call.enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {

                if (response.isSuccessful()) {
                    Leaderboard leaderboard = response.body();
                    leaderboardlist.addAll(leaderboard.getWinners());
                    mAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    Log.i("AG", "onResponse: " + response.body());
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    if (leaderboardlist.size() > 0) {
                        if (leaderboardlist.get(0) != null) {
                            imagetext[0].setText(leaderboardlist.get(0).getUserName());
                            Picasso.get().load(leaderboardlist.get(0).getImageUrl()).placeholder(R.drawable.default_avatar).into(images[0]);
                        }
                        if (leaderboardlist.size() > 1) {
                            if (leaderboardlist.get(1) != null) {
                                imagetext[1].setText(leaderboardlist.get(1).getUserName());
                                Picasso.get().load(leaderboardlist.get(1).getImageUrl()).placeholder(R.drawable.default_avatar).into(images[1]);
                            }
                            if (leaderboardlist.size() > 2) {
                                if (leaderboardlist.get(2) != null) {
                                    imagetext[2].setText(leaderboardlist.get(2).getUserName());
                                    Picasso.get().load(leaderboardlist.get(2).getImageUrl()).placeholder(R.drawable.default_avatar).into(images[2]);
                                }

                            }
                        }
                    }
                }
                for (int i = 0; i < leaderboardlist.size(); i++) {
                    if (leaderboardlist.get(i).getUserName().equals(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERNAME))) {
                        snackbar.setVisibility(View.VISIBLE);
                        username.setText(leaderboardlist.get(i).getUserName());
                        usertime.setText(leaderboardlist.get(i).getTotalTimeTaken() + "");
                        userposition.setText("" + ++i);
                    }
                }
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {
                Log.d("POST_API", t.getCause() + "");
                progressBar.setVisibility(View.GONE);


            }
        });
    }


    public void backbtn(View view) {
        onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
