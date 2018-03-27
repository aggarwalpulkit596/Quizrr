package quizrr.quizrr.quizrr.SignUpActivites;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import quizrr.quizrr.quizrr.Activities.HomeActivity;
import quizrr.quizrr.quizrr.Activities.TourActivity;
import quizrr.quizrr.quizrr.Adapters.MyPreferenceManager;
import quizrr.quizrr.quizrr.QuizApp;
import quizrr.quizrr.com.R;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class SigUpComplete extends AppCompatActivity {

    @BindView(R.id.profileearnertextview)
    RelativeLayout referearned;
    @BindView(R.id.signupusername)
    TextView username;
    @BindView(R.id.viewKonfetti)
    KonfettiView konfettiView;
    @BindView(R.id.completeuserimage)
    CircleImageView userimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up_complete);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        final ViewGroup transitionsContainer = findViewById(R.id.transitions_container);
        final Intent intent = getIntent();
        username.setText("All set " + intent.getStringExtra("username")+"!");
        Picasso.get().load(QuizApp.getPreferenceManager().getString(MyPreferenceManager.USERIMAGE)).into(userimage);


        if (intent.getBooleanExtra("referrer", false)) {
            TransitionSet set = new TransitionSet()
                    .addTransition(new Fade())
                    .addTransition(new Slide())
                    .setDuration(500)
                    .setInterpolator(new FastOutLinearInInterpolator());

            TransitionManager.beginDelayedTransition(transitionsContainer, set);
            referearned.setVisibility(View.VISIBLE);
            konfettiView.build()
                    .addColors(Color.parseColor("#FCE18B"), Color.parseColor("#FF736E"), Color.parseColor("#B68CF1"), Color.parseColor("#F4316C"), Color.parseColor("#006DEA"), Color.parseColor("#39BA9E"), Color.parseColor("#BC3D73"))
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 16f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .stream(300, 5000L);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SigUpComplete.this, TourActivity.class);
                i.putExtra("token", intent.getStringExtra("token"));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                finish();
            }
        }, 5000);

    }
}
