package learnacad.learnacad.com.InfoActivies;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnacad.learnacad.com.Adapters.MyPreferenceManager;
import learnacad.learnacad.com.MODELS.CMS;
import learnacad.learnacad.com.MODELS.userfields;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.REST.ApiClients;
import learnacad.learnacad.com.REST.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsandPolicyActivity extends AppCompatActivity {


    @BindView(R.id.termslinearlayout)
    LinearLayout termslinearlayout;

    @BindView(R.id.titletextview)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsand_policy);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

//        Intent i = getIntent();
//        if (i.getStringExtra("type").equals("terms")) {
//            title.setText("Terms of Use");
//            termstextview.setVisibility(View.VISIBLE);
//            termslinearlayout.setVisibility(View.GONE);
//        } else if (i.getStringExtra("type").equals("policy")) {
//            title.setText("Privacy Policy");
//            termstextview.setVisibility(View.VISIBLE);
//            termslinearlayout.setVisibility(View.GONE);
//        } else if (i.getStringExtra("type").equals("Rules")) {
//            title.setText("Rules");
//            termstextview.setVisibility(View.GONE);
//            termslinearlayout.setVisibility(View.VISIBLE);
//        }
        fetchdata();

    }

    private void fetchdata() {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<CMS> call = apiService.cms();
        call.enqueue(new Callback<CMS>() {
            @Override
            public void onResponse(Call<CMS> call, Response<CMS> response) {
                if (response.isSuccessful()) {
                    CMS cms = response.body();
                    final int N = cms.getRules().size() - 1; // total number of textviews to add

                    final TextView[] myTextViews = new TextView[N];// create an empty array;
                    final TextView[] myTextViews2 = new TextView[N];

                    for (int i = 0; i < N; i++) {
                        // create a new textview
                        final TextView rowTextView = new TextView(TermsandPolicyActivity.this);
                        final TextView heading = new TextView(TermsandPolicyActivity.this);


                        // set some properties of rowTextView or something

                        if ((i + 1) % 2 == 0)
                            rowTextView.setTextColor(Color.parseColor("#ff367d"));
                        else
                            rowTextView.setTextColor(Color.parseColor("#ffd500"));
                        rowTextView.setText(i + 1 + ". " + cms.getRules().get(i).getHeader());
                        rowTextView.setLineSpacing(1.2f, 1.2f);
                        Typeface typeface = ResourcesCompat.getFont(TermsandPolicyActivity.this, R.font.circular_medium);
                        rowTextView.setTypeface(typeface);
                        rowTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                        // set some properties of rowTextView or something
                        heading.setText(cms.getRules().get(i).getText());
                        heading.setLineSpacing(1.2f, 1.2f);
                        heading.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        typeface = ResourcesCompat.getFont(TermsandPolicyActivity.this, R.font.circular_book);
                        rowTextView.setTypeface(typeface);
                        heading.setTextColor(Color.parseColor("#ffffff"));
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                        params.setMargins(10, 20, 34, 60);
                        heading.setLayoutParams(params);


                        // add the textview to the linearlayout
                        termslinearlayout.addView(rowTextView);
                        termslinearlayout.addView(heading);

                        // save a reference to the textview for later
                        myTextViews[i] = rowTextView;
                        myTextViews2[i] = heading;
                    }

                }
            }

            @Override
            public void onFailure(Call<CMS> call, Throwable t) {

            }
        });


    }


    public void backbtn(View view) {
        onBackPressed();
    }
}
