package quizrr.quizrr.quizrr.InfoActivies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import quizrr.quizrr.quizrr.Adapters.MyPreferenceManager;
import quizrr.quizrr.quizrr.Adapters.UpcomingQuizAdapter;
import quizrr.quizrr.quizrr.MODELS.Quiz;
import quizrr.quizrr.quizrr.QuizApp;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.REST.ApiClients;
import quizrr.quizrr.quizrr.REST.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutureeventsActivity extends AppCompatActivity {
    ArrayList<Quiz> list;
    UpcomingQuizAdapter mAdapter;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futureevents);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        listView = findViewById(R.id.listview);
        textView = findViewById(R.id.empty);
        list = new ArrayList<>();
        fetchquizes();
        mAdapter = new UpcomingQuizAdapter(this, list);
        listView.setAdapter(mAdapter);
    }

    private void fetchquizes() {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<List<Quiz>> call = apiService.futureevents(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN));
        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                if (response.isSuccessful()) {
                    List<Quiz> list1 = response.body();
                    Collections.reverse(list1);
                    list.addAll(list1);
                    listView.smoothScrollToPosition(list1.size() - 1);
                    mAdapter.notifyDataSetChanged();
                    if (list.size() == 0) {
                        listView.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);

                    }
                }

            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {

            }
        });
    }

    public void backbtn(View view) {
        onBackPressed();
    }
}
