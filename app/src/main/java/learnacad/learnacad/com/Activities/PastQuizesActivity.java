package learnacad.learnacad.com.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.Adapters.PastQuizesAdapter;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.REST.ApiClients;
import learnacad.learnacad.com.REST.ApiInterface;
import learnacad.learnacad.com.MODELS.PastQuizes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PastQuizesActivity extends AppCompatActivity {

    ArrayList<PastQuizes> list;
    PastQuizesAdapter mAdapter;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_quizes);
        listView = findViewById(R.id.listview);
        textView = findViewById(R.id.empty);
        list = new ArrayList<>();
        fetchquizes();
        mAdapter = new PastQuizesAdapter(this, list);
        listView.setAdapter(mAdapter);
    }

    private void fetchquizes() {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<List<PastQuizes>> call = apiService.pastquiz(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN));
        call.enqueue(new Callback<List<PastQuizes>>() {
            @Override
            public void onResponse(Call<List<PastQuizes>> call, Response<List<PastQuizes>> response) {
                if (response.isSuccessful()) {
                    List<PastQuizes> list1 = response.body();
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
            public void onFailure(Call<List<PastQuizes>> call, Throwable t) {

            }
        });
    }

    public void backbtn(View view) {
        onBackPressed();
    }
}
