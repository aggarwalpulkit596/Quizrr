package quizrr.quizrr.quizrr.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import quizrr.quizrr.quizrr.Adapters.MyPreferenceManager;
import quizrr.quizrr.quizrr.Constants;
import quizrr.quizrr.quizrr.InfoActivies.TermsandPolicyActivity;
import quizrr.quizrr.quizrr.QuizApp;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.SignUpActivites.SignUpActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.splashlinearlayout)
    LinearLayout splashLinearLayout;

    @BindView(R.id.transitions_container)
    RelativeLayout splashrelativeLayout;

    @BindView(R.id.splashimage)
    RelativeLayout splashimage;

    @BindView(R.id.splashrulestextview)
    TextView splashrulestextview;

    @BindView(R.id.splashpolicytextview)
    TextView splashpolicytextview;

    @BindView(R.id.splashtermstextview)
    TextView splashtermstextview;

    @BindView(R.id.splashgetstarted)
    Button splashgetstarted;
    String accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        QuizApp.getPreferenceManager().getString(MyPreferenceManager.FIRSTTIME).equals("false");
        ButterKnife.bind(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Constants.isConnected(SplashActivity.this)) {
                    Snackbar snackBar = Snackbar.make(splashLinearLayout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
                    snackBar.show();
                    if (Constants.isConnected(SplashActivity.this)) {
                        snackBar.dismiss();
                    }
                    return;
                } else {
                    if (QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN) != null) {
                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                        accessToken = QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN);
                        i.putExtra("token", accessToken);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(i);
                        finish();
                    } else {
                        splashimage.setVisibility(View.GONE);
                        splashrelativeLayout.setVisibility(View.VISIBLE);
                        splashrelativeLayout.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.custom_entry));
                    }
                    int PERMISSION_ALL = 1;
                    String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.CAMERA};
                    if (!hasPermissions(SplashActivity.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS, PERMISSION_ALL);
                    }
                }
            }
        }, 3000);


        splashtermstextview.setOnClickListener(this);
        splashgetstarted.setOnClickListener(this);
        splashrulestextview.setOnClickListener(this);
        splashpolicytextview.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.splashtermstextview:
                Intent i = new Intent(SplashActivity.this, TermsandPolicyActivity.class);
//                i.putExtra("type", "terms");
//                startActivity(i);
                break;
            case R.id.splashpolicytextview:
//                i = new Intent(SplashActivity.this, TermsandPolicyActivity.class);
//                i.putExtra("type", "policy");
//                startActivity(i);
                break;
            case R.id.splashrulestextview:
//                i = new Intent(SplashActivity.this, TermsandPolicyActivity.class);
//                i.putExtra("type", "rules");
//                startActivity(i);
                break;
            case R.id.splashgetstarted:
                i = new Intent(SplashActivity.this, SignUpActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                break;
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
