package learnacad.learnacad.com.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnacad.learnacad.com.Adapters.ViewPagerAdapter;
import learnacad.learnacad.com.Adapters.ViewPagerCustomDuration;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.SignUpActivites.SignUpActivity;

public class TourActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.viewpager)
    ViewPagerCustomDuration viewPager;
    @BindView(R.id.viewPagerCountDots)
    LinearLayout pager_indicator;
    @BindView(R.id.nextbutton)
    Button nextButton;

    ViewPagerAdapter mAdapter;
    int dotsCount;
    ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        mAdapter = new ViewPagerAdapter(TourActivity.this, 2);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        viewPager.setScrollDuration(150);
        setUiPageViewController();

    }

    private void setUiPageViewController() {
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditemround));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    20, 20, 1
            );
            params.setMargins(5, 0, 5, 0);

            pager_indicator.addView(dots[i], params);
            final int finalI = i;
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(finalI);
                }
            });
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditemround));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditemround));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditemround));

    }

    @Override
    public void onPageSelected(int position) {
        if (position == dotsCount - 1)
            nextButton.setVisibility(View.VISIBLE);
        else
            nextButton.setVisibility(View.GONE);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void nextbtn(View view) {

//        Intent i = new Intent(TourActivity.this, SignUpActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        startActivity(i);
        finish();
    }


}