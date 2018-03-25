package learnacad.learnacad.com.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnacad.learnacad.com.Adapters.ViewPagerAdapter;
import learnacad.learnacad.com.R;

public class QuizReviewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {


    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.viewPagerCountDots)
    LinearLayout pager_indicator;
    @BindView(R.id.prevbtn)
    TextView prevbtn;
    @BindView(R.id.nextbtn)
    TextView nextbtn;
    @BindView(R.id.topictextview)
    TextView topictextview;

    ViewPagerAdapter mAdapter;
    int dotsCount;
    ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_review);
        ButterKnife.bind(this);
        Intent i = getIntent();
        topictextview.setText(i.getStringExtra("topic"));
        mAdapter = new ViewPagerAdapter(QuizReviewActivity.this, i.getStringExtra("quizid"),i.getIntExtra("noofquestions",4));
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(5);
        prevbtn.setOnClickListener(this);
        nextbtn.setOnClickListener(this);
        setUiPageViewController();

    }

    private void setUiPageViewController() {
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    100,
                    25, 1
            );
            params.setMargins(4, 10, 4, 0);

            pager_indicator.addView(dots[i], params);
            final int finalI = i;
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(finalI);
                }
            });
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem));

        if (position + 1 == dotsCount) {

            nextbtn.setText("Finish");
            prevbtn.setTextColor(Color.parseColor("#ffffff"));
        } else if (position == 0) {
            nextbtn.setText("Next");
            prevbtn.setTextColor(Color.parseColor("#A9A9A9"));
            nextbtn.setTextColor(Color.parseColor("#ffffff"));
        } else {
            nextbtn.setText("Next");
            nextbtn.setTextColor(Color.parseColor("#ffffff"));
            prevbtn.setTextColor(Color.parseColor("#ffffff"));

        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextbtn:
                if (nextbtn.getText().equals("Finish")) {
                    finish();
                } else
                    viewPager.setCurrentItem((viewPager.getCurrentItem() < dotsCount)
                            ? viewPager.getCurrentItem() + 1 : 0);
                break;

            case R.id.prevbtn:
                viewPager.setCurrentItem((viewPager.getCurrentItem() > 0)
                        ? viewPager.getCurrentItem() - 1 : 0);
                break;

        }
    }

    public void backbtn(View view) {
        onBackPressed();
    }
}
